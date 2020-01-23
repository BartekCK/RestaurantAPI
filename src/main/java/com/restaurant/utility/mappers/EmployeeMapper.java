package com.restaurant.utility.mappers;

import com.restaurant.commands.response.EmployeeView;
import com.restaurant.models.Employee;

public class EmployeeMapper {

    public static EmployeeView mapEmployeeToEmployeeView(Employee employee) {
        return EmployeeView.builder()
                .userId(employee.getUserId().getUserId())
                .employeeId(employee.getEmployeeId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .salary(employee.getSalary())
                .build();
    }
}
