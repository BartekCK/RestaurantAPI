package com.restaurant.views;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpinionView {
    private Long opinionId;
    private Long userId;
    private String userName;
    private RestaurantView restaurant;
    private String textOpinion;
}
