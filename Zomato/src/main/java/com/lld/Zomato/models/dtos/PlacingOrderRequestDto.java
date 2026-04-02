package com.lld.Zomato.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlacingOrderRequestDto {
    private Long userId;
    private Long restaurantId;
    private List<Long> menuItemIdList;
}
