package com.restaurant.services;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.commands.response.OpinionView;
import com.restaurant.repositories.OpinionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpinionServiceImpl implements OpinionService {
    private final OpinionRepository opinionRepository;

    @Override
    public Long addOpinion(OpinionDTO opinionDTO) {
        return opinionRepository.saveOpinion(opinionDTO);
    }

    @Override
    public OpinionView updateOpinion(Long opinionId, OpinionDTO opinionDTO) {
        return opinionRepository.updateOpinion(opinionId, opinionDTO);
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
