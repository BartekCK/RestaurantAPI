package com.restaurant.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class OpinionCommand {
    @NotEmpty
    private Long customerId;
    @NotEmpty
    private Long restaurantId;
    @NotEmpty
    private String textOpinion;

    @JsonCreator
    public OpinionCommand(@JsonProperty(value = "customerId", required = true) Long customerId,
                          @JsonProperty(value = "restaurantId", required = true) Long restaurantId,
                          @JsonProperty(value = "textOpinion", required = true) String textOpinion) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.textOpinion = textOpinion;
    }
}
