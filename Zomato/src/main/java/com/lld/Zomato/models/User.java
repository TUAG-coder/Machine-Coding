package com.lld.Zomato.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private Integer phoneNumber;
    private List<Address> addressList;
    private List<Order> orderList;
    private Double walletBalance;
    @Transient
    private Location currentLocation;
}
