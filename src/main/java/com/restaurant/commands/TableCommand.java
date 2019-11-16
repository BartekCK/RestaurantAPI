package com.restaurant.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Value
@Getter
public class TableCommand {
    @Min(value = 1)
    @Max(value = 10)
    @NotEmpty
    private int seatsNumber;
    @NotEmpty
    private Integer restaurantId;

    @JsonCreator
    public TableCommand(@JsonProperty(value = "seatsNumber", required = true) int seatsNumber,
                             @JsonProperty(value = "restaurantId", required = true) Integer restaurantId) {
        this.seatsNumber = seatsNumber;
        this.restaurantId = restaurantId;
    }
}
