package com.restaurant.services;

import com.restaurant.commands.OpinionCommand;
import com.restaurant.repositories.OpinionRepository;
import com.restaurant.views.OpinionView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpinionServiceImpl implements OpinionService {
    private final OpinionRepository opinionRepository;

    @Override
    public Long addOpinion(OpinionCommand opinionCommand) {
        return opinionRepository.saveOpinion(opinionCommand);
    }

    @Override
    public OpinionView updateOpinion(Long opinionId, OpinionCommand opinionCommand) {
        return opinionRepository.updateOpinion(opinionId, opinionCommand);
    }

    @Override
    public OpinionView getOpinionById(Long opinionId) {
        return opinionRepository.getOpinionById(opinionId);
    }

    @Override
    public List<OpinionView> getAllOpinionsByRestaurantId(Integer restaurantId) {
        return opinionRepository.getAllOpinionsByRestaurantId(restaurantId);
    }

    @Override
    public List<OpinionView> getAllOpinionsByUserId(Long userId) {
        return opinionRepository.getAllOpinionsByUserId(userId);
    }
}
