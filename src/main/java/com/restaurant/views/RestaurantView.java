package com.restaurant.views;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantView {
    private Integer restaurantId;
    private String city;
    private String street;
}
