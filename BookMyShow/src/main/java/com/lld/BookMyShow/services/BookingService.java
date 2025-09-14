package com.lld.BookMyShow.services;

import com.lld.BookMyShow.enums.BookingStatus;
import com.lld.BookMyShow.enums.ShowSeatStatus;
import com.lld.BookMyShow.exceptions.ShowNotFoundException;
import com.lld.BookMyShow.exceptions.ShowSeatNotFoundException;
import com.lld.BookMyShow.exceptions.UserNotFoundException;
import com.lld.BookMyShow.models.Booking;
import com.lld.BookMyShow.models.Show;
import com.lld.BookMyShow.models.ShowSeat;
import com.lld.BookMyShow.models.User;
import com.lld.BookMyShow.repositories.BookingRepository;
import com.lld.BookMyShow.repositories.ShowRepository;
import com.lld.BookMyShow.repositories.ShowSeatRepository;
import com.lld.BookMyShow.repositories.UserRepository;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculator priceCalculator;
    private BookingRepository bookingRepository;

    public BookingService(
            UserRepository userRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            PriceCalculator priceCalculator,
            BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculator = priceCalculator;
        this.bookingRepository = bookingRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds)
            throws UserNotFoundException, ShowNotFoundException, ShowSeatNotFoundException {
        /*
        1. Get the user with the given user id
        2. Get the show with given show id
        3. Get the list of showSeats with given ids
        -------------TAKE A LOCK------------
        4. Check if all the seats are available or not
        5. If not, throw an exception
        6. If yes, mark the seats as BLOCKED
        -----------RELEASE THE LOCK---------
        7. Save the changes to the DB
        8. Create booking with PENDING status (save Booking object to DB)
        9. Return the booking object
         */

        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User doesn't exist for the given id");
        }
        User user = optionalUser.get();

        Optional<Show> optionalShow = this.showRepository.findById(showId);
        if (optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show doesn't exist for the given id.");
        }
        Show show = optionalShow.get();

        List<ShowSeat> showSeats = this.showSeatRepository.findAllById(showSeatIds);
        if (showSeats.size() == 0) {
            throw new ShowSeatNotFoundException("Seats doesn't exist for the given ids");
        }
        for (ShowSeat showSeat: showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotFoundException("Requested seats are not available for the given show");
            }
        }

        for (ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }

        // Here we're using saveAll() method for Update and not Insert
        this.showSeatRepository.saveAll(showSeats);

        Booking booking = new Booking();
        booking.setShow(show);
        booking.setUser(user);
        booking.setShowSeatList(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookingNumber("12766437");
        booking.setCreatedAt(new Date());
        booking.setPayments(new ArrayList<>());

        booking.setAmount(this.priceCalculator.calculatePrice(show, showSeats));

        booking = this.bookingRepository.save(booking);
        return booking;
    }
}
