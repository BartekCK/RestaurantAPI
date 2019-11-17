package com.restaurant.views;

import com.restaurant.models.Order;
import com.restaurant.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class EmployeeView {

    private Long employeeId;

    private User userId;

    private String name;

    private String surname;

    private double salary;

    private Set<Order> orderSet;
}
