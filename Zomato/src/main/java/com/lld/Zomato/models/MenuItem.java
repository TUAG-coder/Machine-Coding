package com.lld.Zomato.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MenuItem extends BaseModel{
    private String name;
    private String description;
    private String cuisineType;
    private Double rating;
    private Double price;
    private Boolean isAvailable;
    private String imageUrl;
}
