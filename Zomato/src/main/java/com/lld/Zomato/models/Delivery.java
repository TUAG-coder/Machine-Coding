package com.lld.Zomato.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Delivery extends BaseModel{
    private Order order;
    private DeliveryPartner deliveryPartner;
    private Date pickupTime;
    private Date dropTime;
    @Transient
    private Location currentLocation;
}
