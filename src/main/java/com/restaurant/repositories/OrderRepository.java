package com.restaurant.repositories;

import com.restaurant.commands.request.OrderDTO;

public interface OrderRepository {
    Long saveOrder(OrderDTO order);
}
