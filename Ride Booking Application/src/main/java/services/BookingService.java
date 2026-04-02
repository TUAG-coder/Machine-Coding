package services;

import enums.BookingStatus;
import enums.PaymentMode;
import enums.PaymentStatus;
import enums.VehicleType;
import events.BookingCreatedEvent;
import events.EventPublisher;
import exceptions.PassengerNotFoundException;
import exceptions.PaymentModeNotFoundException;
import exceptions.VehicleTypeNotFoundException;
import models.Booking;
import models.Location;
import models.Passenger;
import models.Payment;
import repositories.BookingRepository;
import repositories.PassengerRepository;
import repositories.PaymentRepository;

import java.util.Optional;

public class BookingService {
    private PassengerRepository passengerRepository;
    private PaymentRepository paymentRepository;
    private BookingRepository bookingRepository;
    private EventPublisher eventPublisher;


    public BookingService(PassengerRepository passengerRepository,
                          PaymentRepository paymentRepository,
                          BookingRepository bookingRepository,
                          EventPublisher eventPublisher) {
        this.passengerRepository = passengerRepository;
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.eventPublisher = eventPublisher;
    }

    public Booking bookRide(
            Long passengerId,
            Location source,
            Location destination,
            String vehicleType,
            double amount,
            String paymentMode) throws PassengerNotFoundException, VehicleTypeNotFoundException, PaymentModeNotFoundException {
        Optional<Passenger> optionalPassenger = this.passengerRepository.findById(passengerId);

        if (optionalPassenger.isEmpty()) {
            throw new PassengerNotFoundException("Passenger not found");
        }

        Passenger passenger = optionalPassenger.get();

        Optional<VehicleType> optionalVehicleType = VehicleType.getVehicleType(vehicleType);
        if (optionalVehicleType.isEmpty()) {
            throw new VehicleTypeNotFoundException("Vehicle type not found");
        }

        Optional<PaymentMode> optionalPaymentMode = PaymentMode.getPaymentMode(paymentMode);
        if (optionalPaymentMode.isEmpty()) {
            throw new PaymentModeNotFoundException("Payment mode not found");
        }


        Booking booking = new Booking();
        booking.setPassenger(passenger);
        booking.setBookingStatus(BookingStatus.INITIATED);
        booking.setAmount(amount);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentMode(optionalPaymentMode.get());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        this.paymentRepository.createPayment(payment);

        booking.setPayment(payment);
        booking.setSource(source);
        booking.setDestination(destination);
        booking.setVehicleType(optionalVehicleType.get());
        this.bookingRepository.createBooking(booking);

        // async event for driver matching
        this.eventPublisher.publish(new BookingCreatedEvent(booking.getId()));

        return booking;
    }
}


