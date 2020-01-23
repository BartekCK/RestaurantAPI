package com.restaurant.repositories;

import com.restaurant.commands.request.RestaurantDTO;
import com.restaurant.commands.response.RestaurantView;
import com.restaurant.models.Restaurant;
import com.restaurant.repositories.jpa.RestaurantJPARepository;
import com.restaurant.utility.exceptions.RestaurantNotFoundException;
import com.restaurant.utility.mappers.RestaurantMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJPARepository restaurantJPARepository;

    @Override
    public Integer saveRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantJPARepository
                .save(buildRestaurantFromCommand(restaurantDTO))
                .getRestaurantId();
    }

    @Override
    public RestaurantView updateRestaurant(Integer restaurantId, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = getRestaurant(restaurantId);
        Restaurant updatedRestaurant = getUpdatedRestaurant(restaurantDTO, restaurant);
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

    private Restaurant buildRestaurantFromCommand(RestaurantDTO restaurantDTO) {
        return Restaurant.builder()
                .city(restaurantDTO.getCity())
                .street(restaurantDTO.getStreet())
                .build();
    }

    private Restaurant getRestaurant(Integer restaurantId) {
        return restaurantJPARepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    private Restaurant getUpdatedRestaurant(RestaurantDTO restaurantDTO, Restaurant restaurant) {
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setStreet(restaurantDTO.getStreet());
        return restaurant;
    }
}
