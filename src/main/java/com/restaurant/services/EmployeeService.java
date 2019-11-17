package com.restaurant.services;

import com.restaurant.commands.request.EmployeeDTO;
import com.restaurant.views.EmployeeView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<EmployeeView> createEmployee(String username, EmployeeDTO employeeDTO);

    ResponseEntity deleteEmployee(Long employeeId);

    ResponseEntity<EmployeeView> updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    EmployeeView getEmployeeById(Long employeeId);

    List<EmployeeView> getAllEmployees();
}
