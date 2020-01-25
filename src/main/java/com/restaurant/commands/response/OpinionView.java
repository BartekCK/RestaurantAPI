package com.restaurant.commands.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class OpinionView {
    private Long opinionId;
    private Date opinionDate;
    private String userName;
    private RestaurantView restaurant;
    private String textOpinion;
}
