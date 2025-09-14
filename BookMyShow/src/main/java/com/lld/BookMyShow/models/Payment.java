package com.lld.BookMyShow.models;

import com.lld.BookMyShow.enums.PaymentMode;
import com.lld.BookMyShow.enums.PaymentProvider;
import com.lld.BookMyShow.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private String referenceNumber;

    private Double amount;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    private Date time;
}
