package com.restaurant.services;

import com.restaurant.commands.request.EmployeeDTO;
import com.restaurant.commands.response.EmployeeView;
import com.restaurant.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<EmployeeView> createEmployee(String username, EmployeeDTO employeeDTO) {
        return employeeRepository.saveEmployee(username, employeeDTO);
    }

    @Override
    public ResponseEntity deleteEmployee(Long employeeId) {
        return employeeRepository.deleteEmployee(employeeId);
    }

    @Override
    public ResponseEntity<EmployeeView> updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        return employeeRepository.updateEmployee(employeeId, employeeDTO);
    }

    @Override
    public EmployeeView getEmployeeById(Long employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

    @Override
    public List<EmployeeView> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
}
