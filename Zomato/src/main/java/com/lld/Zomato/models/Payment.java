package com.lld.Zomato.models;

import com.lld.Zomato.enums.PaymentMode;
import com.lld.Zomato.enums.PaymentStatus;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private Double amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
}
