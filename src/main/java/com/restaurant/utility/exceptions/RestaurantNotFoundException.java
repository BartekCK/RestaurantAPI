package com.restaurant.utility.exceptions;

import static com.restaurant.utility.constants.ExceptionMessages.RESTAURANT_NOT_FOUND;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Integer restaurantId) {
        super(String.format(RESTAURANT_NOT_FOUND, restaurantId));
    }
}
