package com.restaurant.controllers;

import com.restaurant.commands.DishCommand;
import com.restaurant.services.DishService;
import com.restaurant.views.DishView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("menu")
public class DishController {

    private final DishService dishService;

    @PostMapping("/dish")
    // @PreAuthorize("hasRole('ADMIN')")
    public Long addDishToMenu(@RequestBody @Valid DishCommand dishCommand) {
        return dishService.addDishToMenu(dishCommand);
    }

    @PutMapping("/dish/{dishId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public DishView updateDish(@PathVariable Long dishId, @RequestBody @Valid DishCommand dishCommand) {
        return dishService.updateDish(dishId, dishCommand);
    }

    @GetMapping("/dish/{dishId}")
    public DishView getDishById(@PathVariable Long dishId) {
        return dishService.getDishById(dishId);
    }

    @GetMapping
    public List<DishView> getMenu() {
        return dishService.getAllDishes();
    }

}
