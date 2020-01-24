package com.restaurant.utility.mappers;

import com.restaurant.commands.response.OrderView;
import com.restaurant.models.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderMapper {
    public static OrderView mapOrderToOrderView(Order order) {
        return OrderView.builder()
                .reservationId(order.getReservation().getReservationId())
                .bill(order.getBill())
                .orderedDishes(
                        order.getOrderDishes().stream()
                                .map(DishMapper::mapDishToDishView)
                                .collect(Collectors.toList())
                )
                .status(order.getStatus())
                .orderDate(order.getOrderTimestamp())
                .employeeId(order.getEmployee().getEmployeeId())
                .additionalRemarks(order.getAdditionalRemarks())
                .build();
    }
}
