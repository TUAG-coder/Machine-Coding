package com.lld.Zomato.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem extends BaseModel{
    private MenuItem menuItem;
    private Order order;
    private Integer quantity;
    private Double totalAmount;
}
