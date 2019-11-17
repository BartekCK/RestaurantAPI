package com.restaurant.repositories;

import com.restaurant.commands.OpinionCommand;
import com.restaurant.views.OpinionView;

import java.util.List;

public interface OpinionRepository {
    Long saveOpinion(OpinionCommand opinionCommand);

    OpinionView updateOpinion(Long opinionId, OpinionCommand opinionCommand);

    OpinionView getOpinionById(Long opinionId);

    List<OpinionView> getAllOpinionsByRestaurantId(Integer restaurantId);

    List<OpinionView> getAllOpinionsByUserId(Long userId);
}
