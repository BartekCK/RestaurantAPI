package com.restaurant.repositories;

import com.restaurant.commands.request.EmployeeDTO;
import com.restaurant.commands.response.EmployeeView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeRepository {

    ResponseEntity<EmployeeView> saveEmployee(String username, EmployeeDTO employeeDTO);

    ResponseEntity deleteEmployee(Long employeeId);

    ResponseEntity<EmployeeView> updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    EmployeeView getEmployeeById(Long employeeId);

    List<EmployeeView> getAllEmployees();


}
