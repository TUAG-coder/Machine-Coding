package com.lld.Zomato.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel{
    private String pincode;
    private String street;
    private String city;
    private Location geoCoordinates;
}
