package com.lld.Zomato.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Cache-only class
@Getter
@Setter
public class Cart extends BaseModel{
    private User user;
    private Restaurant restaurant;
    private Double totalAmount;
    private List<CartItem> cartItems;
}
