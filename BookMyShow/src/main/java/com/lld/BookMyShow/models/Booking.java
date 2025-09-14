package com.lld.BookMyShow.models;

import com.lld.BookMyShow.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private String bookingNumber;

    @ManyToOne
    private User user;

    private Double amount;

    @ManyToOne
    private Show show;

    @ManyToMany
    private List<ShowSeat> showSeatList;

    @OneToMany
    private List<Payment> payments; // to support partial payments

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}
