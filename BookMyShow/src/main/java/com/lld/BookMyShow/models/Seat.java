package com.lld.BookMyShow.models;

import com.lld.BookMyShow.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatNumber;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    private Integer rowNum;

    private Integer columnNum;
}
