package com.lld.Zomato.models;

import com.lld.Zomato.enums.RestaurantAvailability;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Restaurant extends BaseModel{
    private String name;
    private Address address;
    private List<MenuItem> menuItems;
    private Double rating;
    private Double deliveryRadiusInKm;
    private RestaurantAvailability availability;
    private List<String> cuisineTypes;
}
