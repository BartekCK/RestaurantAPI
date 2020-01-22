package com.restaurant.controllers;

import com.restaurant.commands.request.EmployeeDTO;
import com.restaurant.services.EmployeeService;
import com.restaurant.commands.response.EmployeeView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private EmployeeService employeeService;

    @PostMapping("/employees/{username}")//OK
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createEmployee(@PathVariable String username, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(username, employeeDTO);
    }

    @GetMapping("/employees/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public EmployeeView getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/employees")
    //@PreAuthorize("hasRole('ADMIN')")
    public Iterable<EmployeeView> getAllUsers() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/employees/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeView> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }

    @DeleteMapping("/employees/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteEmployee(@PathVariable Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

}
