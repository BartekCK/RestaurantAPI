package com.restaurant.repositories.jpa;

import com.restaurant.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishJPARepository extends JpaRepository<Dish, Long> {
}
