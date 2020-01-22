package com.restaurant.repositories;

import com.restaurant.commands.request.DishDTO;
import com.restaurant.models.Dish;
import com.restaurant.repositories.jpa.DishJPARepository;
import com.restaurant.utility.exceptions.DishNotFoundException;
import com.restaurant.utility.mappers.DishMapper;
import com.restaurant.commands.response.DishView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class DishRepositoryImpl implements DishRepository {

    private final DishJPARepository dishJPARepository;

    @Override
    public Long saveDish(DishDTO dishDTO) {
        return dishJPARepository.save(buildDishFromCommand(dishDTO)).getDishId();

    }

    @Override
    public DishView updateDish(Long dishId, DishDTO dishDTO) {
        Dish dish = getDish(dishId);
        Dish updatedDish = getUpdatedDish(dishDTO, dish);
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

    private Dish buildDishFromCommand(DishDTO dishDTO) {
        return Dish.builder()
                .dishName(dishDTO.getDishName())
                .dishDescription(dishDTO.getDishDescription())
                .grossPrice(dishDTO.getGrossPrice())
                .build();
    }

    private Dish getDish(Long dishId) {
        return dishJPARepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException(dishId));
    }

    private Dish getUpdatedDish(DishDTO dishDTO, Dish dish) {
        dish.setDishName(dishDTO.getDishName());
        dish.setDishDescription(dishDTO.getDishDescription());
        dish.setGrossPrice(dishDTO.getGrossPrice());
        dish.setCookingTime(dishDTO.getCookingTime());
        return dish;
    }
}
