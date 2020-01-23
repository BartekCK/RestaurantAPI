package com.restaurant.utility.mappers;

import com.restaurant.commands.response.RestaurantView;
import com.restaurant.models.Restaurant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestaurantMapper {
    public static RestaurantView mapRestaurantToRestaurantView(Restaurant restaurant) {
        return RestaurantView.builder()
                .restaurantId(restaurant.getRestaurantId())
                .city(restaurant.getCity())
                .street(restaurant.getStreet())
                .build();
    }
}

