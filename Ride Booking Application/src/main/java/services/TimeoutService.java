package services;

import exceptions.BookingNotFoundException;
import models.Booking;
import models.Driver;
import repositories.BookingRepository;
import strategies.DriverMatchingStrategy;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeoutService {

    private ScheduledExecutorService scheduler;
    private RetryService retryService;
    private BookingRepository bookingRepository;
    private DriverMatchingStrategy driverMatchingStrategy;
    private DriverNotificationService driverNotificationService;

    public TimeoutService(RetryService retryService,
                          BookingRepository bookingRepository,
                          DriverMatchingStrategy driverMatchingStrategy,
                          DriverNotificationService driverNotificationService) {
        this.scheduler = Executors.newScheduledThreadPool(5);
        this.retryService = retryService;
        this.bookingRepository = bookingRepository;
        this.driverMatchingStrategy = driverMatchingStrategy;
        this.driverNotificationService = driverNotificationService;
    }

    public void startTimeout(Long bookingId) {
        /*
        scheduler.schedule(task, 10, TimeUnit.SECONDS) means "run once after 10 seconds"

        We're calling startTimeout after notifying drivers, so we want to wait for 10 seconds before retrying again.

        It makes no sense to send retry request immediately without giving drivers time to respond
         */
        scheduler.schedule(() -> {
            try {
                handleTimeout(bookingId);
            } catch (BookingNotFoundException e) {
                throw new RuntimeException(e);
            }
        }, 10, TimeUnit.SECONDS);
    }

    private void handleTimeout(Long bookingId) throws BookingNotFoundException {
        Optional<Booking> optionalBooking = this.bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found");
        }

        Booking booking = optionalBooking.get();

        // check if retrying is required
        if (retryService.shouldRetry(booking)) {
            // get available drivers
            List<Driver> drivers = this.driverMatchingStrategy.match(booking.getSource(), booking.getVehicleType());

            // notify drivers
            for (Driver driver: drivers) {
                this.driverNotificationService.notify(driver, booking);
            }

            // schedule again if no driver has accepted the ride and retry count is still available
            startTimeout(bookingId);
        }
    }
}
