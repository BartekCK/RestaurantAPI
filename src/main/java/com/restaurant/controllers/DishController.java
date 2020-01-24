package com.restaurant.controllers;

import com.restaurant.commands.response.DishView;
import com.restaurant.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/menu")
public class DishController {

    private final DishService dishService;

    @GetMapping("{dishId}")
    public DishView getDishById(@PathVariable Long dishId) {
        return dishService.getDishById(dishId);
    }

    @GetMapping
    public List<DishView> getMenu() {
        return dishService.getAllDishes();
    }

}
