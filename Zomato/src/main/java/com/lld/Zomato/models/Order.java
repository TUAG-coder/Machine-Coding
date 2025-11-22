package com.lld.Zomato.models;

import com.lld.Zomato.enums.OrderStatus;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Order extends BaseModel{
    private User user;
    private Restaurant restaurant;
    private Double amount;
    private OrderStatus orderStatus;
    private Date orderDate;
    private List<Payment> payments;
}
