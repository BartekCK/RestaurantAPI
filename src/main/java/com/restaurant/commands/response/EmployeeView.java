package com.restaurant.commands.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeView {

    private Long employeeId;

    private Long userId;

    private String name;

    private String surname;

    private double salary;

}
