package com.lld.BookMyShow.services;

import com.lld.BookMyShow.exceptions.ShowNotFoundException;
import com.lld.BookMyShow.exceptions.UserNotFoundException;
import com.lld.BookMyShow.models.Booking;
import com.lld.BookMyShow.models.Show;
import com.lld.BookMyShow.models.User;
import com.lld.BookMyShow.repositories.ShowRepository;
import com.lld.BookMyShow.repositories.ShowSeatRepository;
import com.lld.BookMyShow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;

    public BookingService(
            UserRepository userRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds)
            throws UserNotFoundException, ShowNotFoundException {
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

        Booking booking = new Booking();
        booking.setShow(show);
        booking.setUser(user);
//        booking.setShowSeatList();

        return booking;
    }
}
