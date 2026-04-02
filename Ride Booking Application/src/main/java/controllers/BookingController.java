package controllers;

import dtos.ApiResponse;
import dtos.BookingResponseDto;
import dtos.BookingRideRequestDto;
import enums.BookingStatus;
import exceptions.PassengerNotFoundException;
import exceptions.PaymentModeNotFoundException;
import exceptions.VehicleTypeNotFoundException;
import models.Booking;
import services.BookingService;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public ApiResponse<BookingResponseDto> bookRide(BookingRideRequestDto requestDto) {
        try {
            Booking booking = this.bookingService.bookRide(
                    requestDto.getPassengerId(),
                    requestDto.getSource(),
                    requestDto.getDestination(),
                    requestDto.getVehicleType(),
                    requestDto.getAmount(),
                    requestDto.getPaymentMode());

            BookingResponseDto responseDto = new BookingResponseDto();
            responseDto.setBookingId(booking.getId());
            responseDto.setBookingStatus(booking.getBookingStatus().name());
            return new ApiResponse<>(201, "Booking created successfully", responseDto);
        } catch (PassengerNotFoundException exception) {
            return new ApiResponse<>(404, exception.getMessage(), null);
        } catch (VehicleTypeNotFoundException | PaymentModeNotFoundException exception) {
            return new ApiResponse<>(400, exception.getMessage(), null);
        } catch (Exception exception) {
            return new ApiResponse<>(500, exception.getMessage(), null);
        }
    }
}
