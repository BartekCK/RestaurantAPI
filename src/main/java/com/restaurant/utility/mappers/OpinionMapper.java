package com.restaurant.utility.mappers;

import com.restaurant.commands.response.OpinionView;
import com.restaurant.models.Opinion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Calendar;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class OpinionMapper {
    public static OpinionView mapOpinionToOpinionView(Opinion opinion) {
        return OpinionView.builder()
                .opinionId(opinion.getOpinionId())
                .userName(opinion.getCustomer().getUsername())
                .restaurant(RestaurantMapper.mapRestaurantToRestaurantView(opinion.getRestaurant()))
                .textOpinion(opinion.getTextOpinion())
                .opinionDate(Calendar.getInstance().getTime())
                .build();
    }
}
