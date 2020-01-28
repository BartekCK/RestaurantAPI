package com.restaurant.controllers;

import com.restaurant.commands.request.OrderDTO;
import com.restaurant.commands.response.OrderView;
import com.restaurant.models.enums.OrderStatusEnum;
import com.restaurant.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER','USER')")
    public Long order(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @PutMapping("{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER','USER')")
    public Long updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderId, orderDTO);
    }

    @PutMapping("{employeeId}/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER')")
    public OrderStatusEnum updateStatus(@PathVariable Long employeeId, @PathVariable Long orderId, @RequestBody OrderStatusEnum orderStatus) {
        return orderService.changeOrderStatus(orderId, employeeId, orderStatus);
    }

    @GetMapping("{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER','USER')")
    public OrderView getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER')")
    public List<OrderView> getOrders() {
        return orderService.getOrders();
    }

}
