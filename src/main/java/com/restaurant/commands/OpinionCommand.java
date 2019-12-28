package com.restaurant.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

@Value
@Validated
public class OpinionCommand {
    @NonNull
    private Long customerId;
    @NonNull
    private Integer restaurantId;
    @NonNull
    private String textOpinion;

    @JsonCreator
    public OpinionCommand(@JsonProperty(value = "customerId", required = true) Long customerId,
                          @JsonProperty(value = "restaurantId", required = true) Integer restaurantId,
                          @JsonProperty(value = "textOpinion", required = true) String textOpinion) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.textOpinion = textOpinion;
    }
}
