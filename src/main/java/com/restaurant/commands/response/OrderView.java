package com.restaurant.commands.response;

import com.restaurant.models.Employee;
import com.restaurant.models.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderView {
    private Long orderId;
    private double bill;
    private Long reservationId;
    private Long employeeId;
    private List<DishView> orderedDishes;
    private String additionalRemarks;
    private OrderStatusEnum status;
    private Date orderDate;
}
