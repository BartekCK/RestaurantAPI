package com.restaurant.services;

import com.restaurant.commands.DishCommand;
import com.restaurant.views.DishView;

import java.util.List;

public interface DishService {
    Long addDishToMenu(DishCommand dishCommand);

    DishView updateDish(Long dishId, DishCommand dishCommand);

    DishView getDishById(Long dishId);

    List<DishView> getAllDishes();
}
