package com.restaurant.repositories.jpa;

import com.restaurant.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long> {
}
