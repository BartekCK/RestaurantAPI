package com.restaurant.repositories;

import com.restaurant.commands.RestaurantCommand;
import com.restaurant.models.Restaurant;
import com.restaurant.repositories.jpa.RestaurantJPARepository;
import com.restaurant.utility.exceptions.RestaurantNotFoundException;
import com.restaurant.utility.mappers.RestaurantMapper;
import com.restaurant.views.RestaurantView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJPARepository restaurantJPARepository;

    @Override
    public Integer saveRestaurant(RestaurantCommand restaurantCommand) {
        return restaurantJPARepository
                .save(buildRestaurantFromCommand(restaurantCommand))
                .getRestaurantId();
    }

    @Override
    public RestaurantView updateRestaurant(Integer restaurantId, RestaurantCommand restaurantCommand) {
        Restaurant restaurant = getRestaurant(restaurantId);
        Restaurant updatedRestaurant = getUpdatedRestaurant(restaurantCommand, restaurant);
        restaurantJPARepository.save(updatedRestaurant);
        return RestaurantMapper.mapRestaurantToRestaurantView(updatedRestaurant);
    }

    @Override
    public RestaurantView getRestaurantById(Integer restaurantId) {
        return restaurantJPARepository
                .findById(restaurantId)
                .map(RestaurantMapper::mapRestaurantToRestaurantView)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    @Override
    public List<RestaurantView> getAllRestaurants() {
        return restaurantJPARepository
                .findAll()
                .stream()
                .map(RestaurantMapper::mapRestaurantToRestaurantView)
                .collect(Collectors.toList());
    }

    private Restaurant buildRestaurantFromCommand(RestaurantCommand restaurantCommand) {
        return Restaurant.builder()
                .city(restaurantCommand.getCity())
                .street(restaurantCommand.getStreet())
                .build();
    }

    private Restaurant getRestaurant(Integer restaurantId) {
        return restaurantJPARepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    private Restaurant getUpdatedRestaurant(RestaurantCommand restaurantCommand, Restaurant restaurant) {
        restaurant.setCity(restaurantCommand.getCity());
        restaurant.setStreet(restaurantCommand.getStreet());
        return restaurant;
    }
}
