package com.restaurant.services;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.commands.response.OpinionView;

import java.util.List;

public interface OpinionService {
    Long addOpinion(OpinionDTO opinionDTO);

    OpinionView updateOpinion(Long opinionId, OpinionDTO opinionDTO);

    OpinionView getOpinionById(Long opinionId);

    List<OpinionView> getAllOpinionsByRestaurantId(Integer restaurantId);

    List<OpinionView> getAllOpinionsByUserId(Long userId);
}
