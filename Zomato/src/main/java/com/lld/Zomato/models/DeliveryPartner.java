package com.lld.Zomato.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeliveryPartner extends BaseModel{
    private String name;
    private String phoneNumber;
    private Double rating;
    @Transient
    private Location currentLocation;
}
