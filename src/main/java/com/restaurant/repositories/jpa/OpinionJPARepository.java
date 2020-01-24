package com.restaurant.repositories.jpa;

import com.restaurant.models.Opinion;
import com.restaurant.models.Restaurant;
import com.restaurant.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionJPARepository extends JpaRepository<Opinion, Long> {

    boolean existsByCustomerAndRestaurant(User customer, Restaurant restaurant);
}
