package com.restaurant.utility.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {
    public static final String DISH_NOT_FOUND = "Dish with opinionId %s not found";
    public static final String RESTAURANT_NOT_FOUND = "Restaurant with id %s not found";
    public static final String OPINION_SIZE_MESSAGE = "Opinion should have less than 300 signs";
    public static final String TABLE_NOT_FOUND = "Table %s not found";
    public static final String USER_NOT_FOUND = "User with id %s not found";
    public static final String OPINION_NOT_FOUND = "Opinion with id %s not found";
}
