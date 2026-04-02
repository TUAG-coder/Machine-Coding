package services;

import enums.BookingStatus;
import exceptions.BookingNotFoundException;
import exceptions.DriverNotFoundException;
import models.Booking;
import models.Driver;
import repositories.BookingRepository;
import repositories.DriverRepository;

import java.util.Optional;

public class DriverService {
    private DriverRepository driverRepository;
    private BookingRepository bookingRepository;

    public DriverService(DriverRepository driverRepository, BookingRepository bookingRepository) {
        this.driverRepository = driverRepository;
        this.bookingRepository = bookingRepository;
    }
    public boolean acceptRide(Long driverId, Long bookingId) throws DriverNotFoundException, BookingNotFoundException {
        Optional<Driver> optionalDriver = this.driverRepository.findById(driverId);
        if (optionalDriver.isEmpty()) {
            throw new DriverNotFoundException("Driver not found");
        }
        Optional<Booking> optionalBooking = this.bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found");
        }

        Driver driver = optionalDriver.get();
        Booking booking = optionalBooking.get();

        synchronized (booking) {
            if (booking.getDriver() != null) {
                return false;
            }

            if (booking.getBookingStatus() != BookingStatus.SEARCHING_DRIVER) {
                return false;
            }

            booking.assignDriver(driver);

            driver.assignRide();
            return true;
        }
    }
}
