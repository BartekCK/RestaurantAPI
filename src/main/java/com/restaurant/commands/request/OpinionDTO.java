package com.restaurant.commands.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class OpinionDTO {
    @NotEmpty
    private Long customerId;
    @NotEmpty
    private Integer restaurantId;
    @NotEmpty
    private String textOpinion;

    @JsonCreator
    public OpinionDTO(@JsonProperty(value = "customerId", required = true) Long customerId,
                      @JsonProperty(value = "restaurantId", required = true) Integer restaurantId,
                      @JsonProperty(value = "textOpinion", required = true) String textOpinion) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.textOpinion = textOpinion;
    }
}
