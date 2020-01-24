package com.restaurant.repositories.jpa;

import com.restaurant.models.Restaurant;
import com.restaurant.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TableJPARepository extends JpaRepository<Table, Long> {

    Optional<List<Table>> findAllByRestaurant(Restaurant restaurant);

}
