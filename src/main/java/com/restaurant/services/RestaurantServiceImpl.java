package com.restaurant.services;

import com.restaurant.commands.request.RestaurantDTO;
import com.restaurant.repositories.RestaurantRepository;
import com.restaurant.commands.response.RestaurantView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    public final RestaurantRepository restaurantRepository;

    @Override
    public Integer addRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantRepository.saveRestaurant(restaurantDTO);
    }

    @Override
    public RestaurantView updateRestaurant(Integer restaurantId, RestaurantDTO restaurantDTO) {
        return restaurantRepository.updateRestaurant(restaurantId, restaurantDTO);
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
