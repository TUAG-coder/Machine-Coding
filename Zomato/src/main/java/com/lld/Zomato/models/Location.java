package com.lld.Zomato.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Location extends BaseModel{
    private Double latitude;
    private Double longitude;
}
