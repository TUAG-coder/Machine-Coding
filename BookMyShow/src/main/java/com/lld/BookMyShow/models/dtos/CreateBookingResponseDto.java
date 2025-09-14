package com.lld.BookMyShow.models.dtos;

import com.lld.BookMyShow.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingResponseDto {
    private Long bookingId;
    private ResponseStatus responseStatus;
}
