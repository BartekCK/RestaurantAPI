package com.restaurant.controllers;

import com.restaurant.commands.request.OrderDTO;
import com.restaurant.commands.response.DishView;
import com.restaurant.commands.response.OrderView;
import com.restaurant.repositories.OrderService;
import com.restaurant.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Long order(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @GetMapping("/orderId")
    public OrderView getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

}
