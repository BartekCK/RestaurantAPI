package com.restaurant.utility.exceptions;

import static com.restaurant.utility.constants.ExceptionMessages.DISH_NOT_FOUND;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long dishId) {
        super(String.format(DISH_NOT_FOUND, dishId));
    }
}
