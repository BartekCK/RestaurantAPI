package com.restaurant.repositories;

import com.restaurant.commands.request.EmployeeDTO;
import com.restaurant.views.EmployeeView;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository {
    @Transactional
    ResponseEntity<EmployeeView> saveEmployee(String username, EmployeeDTO employeeDTO);

    @Transactional
    ResponseEntity deleteEmployee(Long employeeId);

    ResponseEntity<EmployeeView> updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    EmployeeView getEmployeeById(Long employeeId);

    List<EmployeeView> getAllEmployees();


}
