package com.restaurant.repositories;

import com.restaurant.commands.request.ReservationDTO;
import com.restaurant.views.ReservationView;
import org.springframework.http.ResponseEntity;

public interface ReservationRepository {
    ResponseEntity<ReservationView> createReservationForUser(ReservationDTO reservationDTO, Long userId);

    ResponseEntity<ReservationView> getReservationById(Long reservationId);

    Iterable<ReservationView> getAllUserReservations(Long userId);

    ResponseEntity deleteReservation(Long reservationId);

}
