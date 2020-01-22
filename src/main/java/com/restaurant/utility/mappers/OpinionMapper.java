package com.restaurant.utility.mappers;

import com.restaurant.models.Opinion;
import com.restaurant.commands.response.OpinionView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class OpinionMapper {
    public static OpinionView mapOpinionToOpinionView(Opinion opinion) {
        return OpinionView.builder()
                .opinionId(opinion.getOpinionId())
                .customer(UserMapper.mapUserToUserPrincipal(opinion.getCustomer()))
                .restaurant(RestaurantMapper.mapRestaurantToRestaurantView(opinion.getRestaurant()))
                .textOpinion(opinion.getTextOpinion())
                .build();
    }
}
