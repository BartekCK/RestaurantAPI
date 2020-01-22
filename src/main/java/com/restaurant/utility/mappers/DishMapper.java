package com.restaurant.utility.mappers;

import com.restaurant.models.Dish;
import com.restaurant.commands.response.DishView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class DishMapper {
    public static DishView mapDishToDishView(Dish dish) {
        return DishView.builder()
                .dishId(dish.getDishId())
                .dishName(dish.getDishName())
                .dishDescription(dish.getDishDescription())
                .grossPrice(dish.getGrossPrice())
                .cookingTime(dish.getCookingTime())
                .build();
    }
}
