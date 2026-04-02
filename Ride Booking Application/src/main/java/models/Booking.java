package models;

import enums.BookingStatus;
import enums.VehicleType;

import java.util.Date;

public class Booking extends BaseModel{
    private Payment payment;
    private Date time;
    private double amount;
    private BookingStatus bookingStatus;
    private Passenger passenger;
    private Driver driver;
    private Location source;
    private Location destination;
    private VehicleType vehicleType;
    private int retryCount;

    private static final int MAX_RETRY = 3;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getSource() {
        return source;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /*
    These methods are required to allow valid transitions only.
    Encapsulated transitions inside the Booking entity and guard them with validation logic.
     */

    public void markSearching() {
        if (this.bookingStatus != BookingStatus.INITIATED) {
            throw new IllegalStateException("Invalid state transition");
        }
        this.bookingStatus = BookingStatus.SEARCHING_DRIVER;
    }

    public void assignDriver(Driver driver) {
        if (this.bookingStatus != BookingStatus.SEARCHING_DRIVER) {
            throw new IllegalStateException("Driver can only be assigned when searching");
        }
        this.driver = driver;
        this.bookingStatus = BookingStatus.DRIVER_ASSIGNED;
    }

    public void startRide() {
        if (this.bookingStatus != BookingStatus.DRIVER_ARRIVING) {
            throw new IllegalStateException("Ride cannot start");
        }
        this.bookingStatus = BookingStatus.IN_PROGRESS;
    }

    public void completeRide() {
        if (this.bookingStatus != BookingStatus.IN_PROGRESS) {
            throw new IllegalStateException("Ride not in progress");
        }
        this.bookingStatus = BookingStatus.COMPLETED;
    }

    public void cancel() {
        if (this.bookingStatus == BookingStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel completed ride");
        }
        this.bookingStatus = BookingStatus.CANCELLED;
    }

    public void expire() {
        if (this.bookingStatus != BookingStatus.SEARCHING_DRIVER) {
            throw new IllegalStateException("Only searching bookings can expire");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }

    public boolean isTerminalState() {
        return this.bookingStatus == BookingStatus.COMPLETED
                || this.bookingStatus == BookingStatus.CANCELLED
                || this.bookingStatus == BookingStatus.EXPIRED;
    }

    // ---------- HANDLING RETRY ----------
    public boolean canRetry() {
        if (this.retryCount > MAX_RETRY) {
            return false;
        }
        return true;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public int getRetryCount() {
        return retryCount;
    }
}
