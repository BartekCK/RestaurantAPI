package com.restaurant.commands.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OpinionView {
    private Long opinionId;
    private LocalDateTime opinionDate;
    private String userName;
    private RestaurantView restaurant;
    private String textOpinion;
}
