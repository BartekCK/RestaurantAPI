package com.restaurant.services;

import com.restaurant.commands.DishCommand;
import com.restaurant.repositories.DishRepository;
import com.restaurant.views.DishView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    @Override
    public Long addDishToMenu(DishCommand dishCommand) {
        return dishRepository.saveDish(dishCommand);
    }

    @Override
    public DishView updateDish(Long dishId, DishCommand dishCommand) {
        return dishRepository.updateDish(dishId, dishCommand);
    }

    @Override
    public DishView getDishById(Long dishId) {
        return dishRepository.getDishById(dishId);
    }

    @Override
    public List<DishView> getAllDishes() {
        return dishRepository.getAllDishes();
    }
}
