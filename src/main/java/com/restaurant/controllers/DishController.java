package com.restaurant.controllers;

import com.restaurant.commands.request.DishDTO;
import com.restaurant.commands.response.DishView;
import com.restaurant.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
