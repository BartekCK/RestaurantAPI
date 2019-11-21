package com.restaurant.repositories.jpa;

import com.restaurant.models.Reservation;
import com.restaurant.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationJPARepository extends JpaRepository<Reservation, Long> {

    boolean existsByDateReservationBetweenAndTableEquals(LocalDateTime localDateTimeBefore, LocalDateTime localDateTimeAfter, Table table);

}
