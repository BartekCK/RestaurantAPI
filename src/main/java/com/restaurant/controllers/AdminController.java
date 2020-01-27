package com.restaurant.controllers;

import com.restaurant.commands.request.DishDTO;
import com.restaurant.commands.request.EmployeeDTO;
import com.restaurant.commands.request.RestaurantDTO;
import com.restaurant.commands.request.TableDTO;
import com.restaurant.commands.response.*;
import com.restaurant.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/admin")
@AllArgsConstructor
public class AdminController {

    private UserService userService;
    private EmployeeService employeeService;
    private RestaurantService restaurantService;
    private TableService tableService;
    private DishService dishService;

    @GetMapping("users")
    //@PreAuthorize("hasRole('ADMIN')")
    public Iterable<UserPrincipal> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("employees/{userId}")//OK
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createEmployee(@PathVariable Long userId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(userId, employeeDTO);
    }

    @GetMapping("employees/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public EmployeeView getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("employees")
    //@PreAuthorize("hasRole('ADMIN')")
    public Iterable<EmployeeView> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("employees/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeView> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }

    @DeleteMapping("employees/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteEmployee(@PathVariable Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @PostMapping("restaurants")
    //  @PreAuthorize("hasRole('ADMIN')")
    public Integer addRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PutMapping("restaurants/{restaurantId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public RestaurantView updateRestaurant(@PathVariable Integer restaurantId, @RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(restaurantId, restaurantDTO);
    }

    @PostMapping("tables")
    //@PreAuthorize("hasRole('ADMIN')")
    public Long addTable(@RequestBody @Valid TableDTO tableDTO) {
        return tableService.addTable(tableDTO);
    }

    @PutMapping("tables/{tableId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public TableView updateTable(@PathVariable Long tableId, @RequestBody @Valid TableDTO tableDTO) {
        return tableService.updateTable(tableId, tableDTO);
    }

    @PostMapping("menu")
    // @PreAuthorize("hasRole('ADMIN')")
    public Long addDishToMenu(@RequestBody @Valid DishDTO dishDTO) {
        return dishService.addDishToMenu(dishDTO);
    }

    @PutMapping("menu/{dishId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public DishView updateDish(@PathVariable Long dishId, @RequestBody @Valid DishDTO dishDTO) {
        return dishService.updateDish(dishId, dishDTO);
    }


}
