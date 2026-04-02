package services;

import events.BookingCreatedEvent;
import exceptions.BookingNotFoundException;
import models.Booking;
import models.Driver;
import repositories.BookingRepository;
import strategies.DriverMatchingStrategy;
import strategies.NearestDriverMatchingStrategy;

import java.util.List;
import java.util.Optional;

public class BookingOrchestrator {

    private BookingRepository bookingRepository;
    private DriverMatchingStrategy driverMatchingStrategy;
    private DriverNotificationService notificationService;
    private TimeoutService timeoutService;

    public BookingOrchestrator(
            BookingRepository bookingRepository,
            DriverMatchingStrategy driverMatchingStrategy,
            DriverNotificationService notificationService,
            TimeoutService timeoutService) {
        this.bookingRepository = bookingRepository;
        this.driverMatchingStrategy = driverMatchingStrategy;
        this.notificationService = notificationService;
        this.timeoutService = timeoutService;
    }

    public void handle(BookingCreatedEvent event) throws BookingNotFoundException {

        Optional<Booking> optionalBooking = bookingRepository.findById(event.getBookingId());

        if (optionalBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found");
        }

        Booking booking = optionalBooking.get();

        // change state
        booking.markSearching();

        // find drivers
        List<Driver> drivers =
                driverMatchingStrategy.match(booking.getSource(), booking.getVehicleType());

        // notify drivers
        for (Driver driver : drivers) {
            notificationService.notify(driver, booking);
        }

        // start timeout
        timeoutService.startTimeout(booking.getId());
    }
}
