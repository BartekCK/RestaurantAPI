package com.restaurant.repositories.jpa;

import com.restaurant.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJPARepository extends JpaRepository<Reservation, Long> {
}
