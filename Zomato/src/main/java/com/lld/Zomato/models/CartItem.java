package com.lld.Zomato.models;

import lombok.Getter;
import lombok.Setter;

// Cache-only class
@Getter
@Setter
public class CartItem extends BaseModel{
    private MenuItem menuItem;
    private Integer quantity;
}
