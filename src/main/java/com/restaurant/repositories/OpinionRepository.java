package com.restaurant.repositories;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.commands.response.OpinionView;

import java.util.List;

public interface OpinionRepository {
    Long saveOpinion(Long userId, Integer restaurantId, OpinionDTO opinionDTO);

    OpinionView updateOpinion(Long opinionId, OpinionDTO opinionDTO);

    OpinionView getOpinionById(Long opinionId);

    List<OpinionView> getAllOpinionsByRestaurantId(Integer restaurantId);

    List<OpinionView> getAllOpinionsByUserId(Long userId);
}
