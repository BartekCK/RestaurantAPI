package com.restaurant.repositories;

import com.restaurant.commands.DishCommand;
import com.restaurant.models.Dish;
import com.restaurant.repositories.jpa.DishJPARepository;
import com.restaurant.utility.exceptions.DishNotFoundException;
import com.restaurant.utility.mappers.DishMapper;
import com.restaurant.views.DishView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class DishRepositoryImpl implements DishRepository {

    private final DishJPARepository dishJPARepository;

    @Override
    public Long saveDish(DishCommand dishCommand) {
        return dishJPARepository.save(buildDishFromCommand(dishCommand)).getDishId();

    }

    @Override
    public DishView updateDish(Long dishId, DishCommand dishCommand) {
        Dish dish = getDish(dishId);
        Dish updatedDish = getUpdatedDish(dishCommand, dish);
        dishJPARepository.save(updatedDish);
        return DishMapper.mapDishToDishView(updatedDish);
    }

    @Override
    public DishView getDishById(Long dishId) {
        return dishJPARepository
                .findById(dishId)
                .map(DishMapper::mapDishToDishView)
                .orElseThrow(() -> new DishNotFoundException(dishId));
    }

    @Override
    public List<DishView> getAllDishes() {
        return dishJPARepository
                .findAll()
                .stream()
                .map(DishMapper::mapDishToDishView)
                .collect(Collectors.toList());
    }

    private Dish buildDishFromCommand(DishCommand dishCommand) {
        return Dish.builder()
                .dishName(dishCommand.getDishName())
                .dishDescription(dishCommand.getDishDescription())
                .grossPrice(dishCommand.getGrossPrice())
                .build();
    }

    private Dish getDish(Long dishId) {
        return dishJPARepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException(dishId));
    }

    private Dish getUpdatedDish(DishCommand dishCommand, Dish dish) {
        dish.setDishName(dishCommand.getDishName());
        dish.setDishDescription(dishCommand.getDishDescription());
        dish.setGrossPrice(dishCommand.getGrossPrice());
        dish.setCookingTime(dishCommand.getCookingTime());
        return dish;
    }
}
