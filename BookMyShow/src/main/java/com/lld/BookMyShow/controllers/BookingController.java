package com.lld.BookMyShow.controllers;

import com.lld.BookMyShow.enums.ResponseStatus;
import com.lld.BookMyShow.models.Booking;
import com.lld.BookMyShow.models.dtos.CreateBookingRequestDto;
import com.lld.BookMyShow.models.dtos.CreateBookingResponseDto;
import com.lld.BookMyShow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    BookingService bookingService;
    public BookingController(BookingService bookingService) {
       this.bookingService = bookingService;
    }
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto) {
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();
        try {
            Booking booking =
                    this.bookingService.createBooking(
                            requestDto.getUserId(), requestDto.getShowId(), requestDto.getShowSeatId());
            responseDto.setBookingId(booking.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            responseDto.setResponseStatus(ResponseStatus.ERROR);
        }
        return responseDto;
    }
}
