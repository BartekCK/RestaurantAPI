package com.restaurant.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

@Value
public class RestaurantCommand {
    @NonNull
    private String city;
    @NonNull
    private String street;

    @JsonCreator
    public RestaurantCommand(@JsonProperty(value = "city", required = true) String city,
                             @JsonProperty(value = "street", required = true) String street) {
        this.city = city;
        this.street = street;
    }
}
