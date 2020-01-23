package com.restaurant.commands.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@Getter
public class TableDTO {
    @Min(value = 1)
    @Max(value = 10)
    @NotNull
    private int seatsNumber;
    //@NotEmpty
    private Integer restaurantId;

    @JsonCreator
    public TableDTO(@JsonProperty(value = "seatsNumber", required = true) int seatsNumber,
                    @JsonProperty(value = "restaurantId", required = true) Integer restaurantId) {
        this.seatsNumber = seatsNumber;
        this.restaurantId = restaurantId;
    }
}
