package com.restaurant.services;

import com.restaurant.commands.request.RestaurantDTO;
import com.restaurant.commands.response.RestaurantView;

import java.util.List;

public interface RestaurantService {
    Integer addRestaurant(RestaurantDTO restaurantDTO);

    RestaurantView updateRestaurant(Integer restaurantId, RestaurantDTO restaurantDTO);

    RestaurantView getRestaurantById(Integer restaurantId);

    List<RestaurantView> getAllRestaurants();
}
