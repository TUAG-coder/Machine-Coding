package com.lld.Zomato.controllers;

import com.lld.Zomato.enums.ResponseStatus;
import com.lld.Zomato.models.Order;
import com.lld.Zomato.models.dtos.PlacingOrderRequestDto;
import com.lld.Zomato.models.dtos.PlacingOrderResponseDto;
import com.lld.Zomato.services.OrderService;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public PlacingOrderResponseDto placeOrder(PlacingOrderRequestDto requestDto) {
        PlacingOrderResponseDto responseDto = new PlacingOrderResponseDto();
        try {
            Order order = this.orderService.placeOrder(
                    requestDto.getUserId(),
                    requestDto.getRestaurantId(),
                    requestDto.getMenuItemIdList());
        } catch (Exception exception) {
            responseDto.setResponseStatus(ResponseStatus.ERROR);
        }
        return responseDto;
    }
}
