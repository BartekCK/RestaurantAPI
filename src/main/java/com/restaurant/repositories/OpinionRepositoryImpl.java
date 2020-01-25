package com.restaurant.repositories;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.commands.response.OpinionView;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Calendar;
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
    public Long saveOpinion(Long userId, Integer restaurantId, OpinionDTO opinionDTO) {
        User user = findUser(userId);
        Restaurant restaurant = findRestaurant(restaurantId);
        if (checkUnique(userId, restaurantId))
            throw new OpinionNotFoundException("Opinion exist");
        return opinionJPARepository
                .save(buildOpinionFromCommand(user, restaurant, opinionDTO))
                .getOpinionId();
    }

    @Override
    public OpinionView updateOpinion(Long opinionId, OpinionDTO opinionDTO) {
        Opinion opinion = findOpinion(opinionId);
        Opinion updatedOpinion = getUpdatedOpinion(opinionDTO, opinion);
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

    private Opinion buildOpinionFromCommand(User user, Restaurant restaurant, OpinionDTO opinionDTO) {
        return Opinion.builder()
                .customer(user)
                .restaurant(restaurant)
                .textOpinion(opinionDTO.getTextOpinion())
                .opinionDate(Calendar.getInstance().getTime())
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

    private Opinion getUpdatedOpinion(OpinionDTO opinionDTO, Opinion opinion) {
        opinion.setTextOpinion(opinionDTO.getTextOpinion());
        opinion.setOpinionDate(Calendar.getInstance().getTime());
        return opinion;
    }

    private boolean checkUnique(Long userId, Integer restaurantId) {
        return opinionJPARepository.existsByCustomerAndRestaurant(findUser(userId), findRestaurant(restaurantId));
    }
}
