package com.restaurant.services;

import com.restaurant.commands.request.DishDTO;
import com.restaurant.commands.response.DishView;
import com.restaurant.repositories.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    @Override
    public Long addDishToMenu(DishDTO dishDTO) {
        return dishRepository.saveDish(dishDTO);
    }

    @Override
    public DishView updateDish(Long dishId, DishDTO dishDTO) {
        return dishRepository.updateDish(dishId, dishDTO);
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
