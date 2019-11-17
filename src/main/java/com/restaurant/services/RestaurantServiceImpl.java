package com.restaurant.services;

import com.restaurant.commands.RestaurantCommand;
import com.restaurant.repositories.RestaurantRepository;
import com.restaurant.views.RestaurantView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    public final RestaurantRepository restaurantRepository;

    @Override
    public Integer addRestaurant(RestaurantCommand restaurantCommand) {
        return restaurantRepository.saveRestaurant(restaurantCommand);
    }

    @Override
    public RestaurantView updateRestaurant(Integer restaurantId, RestaurantCommand restaurantCommand) {
        return restaurantRepository.updateRestaurant(restaurantId, restaurantCommand);
    }

    @Override
    public RestaurantView getRestaurantById(Integer restaurantId) {
        return restaurantRepository.getRestaurantById(restaurantId);
    }

    @Override
    public List<RestaurantView> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }
}
