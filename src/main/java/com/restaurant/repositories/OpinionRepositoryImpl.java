package com.restaurant.repositories;

import com.restaurant.commands.OpinionCommand;
import com.restaurant.models.Opinion;
import com.restaurant.models.Restaurant;
import com.restaurant.models.User;
import com.restaurant.repositories.jpa.OpinionJPARepository;
import com.restaurant.repositories.jpa.RestaurantJPARepository;
import com.restaurant.repositories.jpa.UserJPARepository;
import com.restaurant.utility.exceptions.OpinionNotFoundException;
import com.restaurant.utility.exceptions.RestaurantNotFoundException;
import com.restaurant.utility.exceptions.UserNotFoundException;
import com.restaurant.utility.mappers.OpinionMapper;
import com.restaurant.views.OpinionView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class OpinionRepositoryImpl implements OpinionRepository {

    private final OpinionJPARepository opinionJPARepository;
    private final UserJPARepository userJPARepository;
    private final RestaurantJPARepository restaurantJPARepository;

    @Override
    public Long saveOpinion(OpinionCommand opinionCommand) {
        return opinionJPARepository
                .save(buildOpinionFromCommand(opinionCommand))
                .getOpinionId();
    }

    @Override
    public OpinionView updateOpinion(Long opinionId, OpinionCommand opinionCommand) {
        Opinion opinion = findOpinion(opinionId);
        Opinion updatedOpinion = getUpdatedOpinion(opinionCommand, opinion);
        opinionJPARepository.save(updatedOpinion);
        return OpinionMapper.mapOpinionToOpinionView(updatedOpinion);
    }

    @Override
    public OpinionView getOpinionById(Long opinionId) {
        return opinionJPARepository
                .findById(opinionId)
                .map(OpinionMapper::mapOpinionToOpinionView)
                .orElseThrow(() -> new OpinionNotFoundException(opinionId));
    }

    @Override
    public List<OpinionView> getAllOpinionsByRestaurantId(Integer restaurantId) {
        return opinionJPARepository
                .findAll()
                .stream()
                .filter(opinion -> opinion.getRestaurant().getRestaurantId().equals(restaurantId))
                .map(OpinionMapper::mapOpinionToOpinionView)
                .collect(Collectors.toList());
    }

    @Override
    public List<OpinionView> getAllOpinionsByUserId(Long userId) {
        return opinionJPARepository
                .findAll()
                .stream()
                .filter(opinion -> opinion.getCustomer().getUserId().equals(userId))
                .map(OpinionMapper::mapOpinionToOpinionView)
                .collect(Collectors.toList());
    }

    private Opinion buildOpinionFromCommand(OpinionCommand opinionCommand) {
        Optional<User> customer = userJPARepository.findById(opinionCommand.getCustomerId());
        if (!customer.isPresent())
            throw new UserNotFoundException(opinionCommand.getCustomerId());
        Restaurant restaurant = findRestaurant(opinionCommand.getRestaurantId());
        return Opinion.builder()
                .customer(customer.get())
                .restaurant(restaurant)
                .textOpinion(opinionCommand.getTextOpinion())
                .build();
    }

    private Restaurant findRestaurant(Integer restaurantId) {
        Optional<Restaurant> restaurant = restaurantJPARepository.findById(restaurantId);
        if (!restaurant.isPresent())
            throw new RestaurantNotFoundException(restaurantId);
        return restaurant.get();
    }

    private Opinion findOpinion(Long opinionId) {
        return opinionJPARepository.findById(opinionId)
                .orElseThrow(() -> new OpinionNotFoundException(opinionId));
    }

    private User findUser(Long userId) {
        return userJPARepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private Opinion getUpdatedOpinion(OpinionCommand opinionCommand, Opinion opinion) {
        opinion.setRestaurant(findRestaurant(opinionCommand.getRestaurantId()));
        opinion.setCustomer(findUser(opinionCommand.getCustomerId()));
        opinion.setTextOpinion(opinionCommand.getTextOpinion());
        return opinion;
    }
}
