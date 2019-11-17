package com.restaurant.repositories.jpa;

import com.restaurant.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJPARepository extends JpaRepository<Restaurant, Integer> {
}
