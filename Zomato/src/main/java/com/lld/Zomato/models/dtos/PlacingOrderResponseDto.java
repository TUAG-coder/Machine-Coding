package com.lld.Zomato.models.dtos;

import com.lld.Zomato.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlacingOrderResponseDto {
    private ResponseStatus responseStatus;
}
