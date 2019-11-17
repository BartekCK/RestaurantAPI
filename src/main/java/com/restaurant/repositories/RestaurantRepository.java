package com.restaurant.repositories;

import com.restaurant.commands.RestaurantCommand;
import com.restaurant.views.RestaurantView;

import java.util.List;

public interface RestaurantRepository {
    Integer saveRestaurant(RestaurantCommand restaurantCommand);

    RestaurantView updateRestaurant(Integer restaurantId, RestaurantCommand restaurantCommand);

    RestaurantView getRestaurantById(Integer restaurantId);

    List<RestaurantView> getAllRestaurants();

}
