package com.lld.Zomato.services;

import com.lld.Zomato.models.Order;
import com.lld.Zomato.repositories.MenuItemRepository;
import com.lld.Zomato.repositories.OrderRepository;
import com.lld.Zomato.repositories.RestaurantRepository;
import com.lld.Zomato.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
    private OrderRepository orderRepository;

    public OrderService(UserRepository userRepository,
                        RestaurantRepository restaurantRepository,
                        MenuItemRepository menuItemRepository,
                        OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Long userId, Long restaurantId, List<Long> menuItemsIdList) {
        return null;
    }
}
