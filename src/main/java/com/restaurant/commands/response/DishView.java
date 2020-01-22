package com.restaurant.commands.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishView {
    private Long dishId;
    private String dishName;
    private String dishDescription;
    private double grossPrice;
    private int cookingTime;
}
