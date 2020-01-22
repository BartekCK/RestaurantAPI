package com.restaurant.services;

import com.restaurant.commands.request.DishDTO;
import com.restaurant.commands.response.DishView;

import java.util.List;

public interface DishService {
    Long addDishToMenu(DishDTO dishDTO);

    DishView updateDish(Long dishId, DishDTO dishDTO);

    DishView getDishById(Long dishId);

    List<DishView> getAllDishes();
}
