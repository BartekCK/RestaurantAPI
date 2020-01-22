package com.restaurant.commands.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class RestaurantDTO {
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;

    @JsonCreator
    public RestaurantDTO(@JsonProperty(value = "city", required = true) String city,
                         @JsonProperty(value = "street", required = true) String street) {
        this.city = city;
        this.street = street;
    }
}
