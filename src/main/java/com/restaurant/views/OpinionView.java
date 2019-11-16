package com.restaurant.views;

import com.restaurant.commands.request.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpinionView {
    private Long opinionId;
    private UserDTO customer;
    private RestaurantView restaurant;
    private String textOpinion;
}
