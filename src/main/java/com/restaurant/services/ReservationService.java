package com.restaurant.services;

import com.restaurant.commands.request.ReservationDTO;
import com.restaurant.commands.response.ReservationView;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    ResponseEntity<ReservationView> makeReservation(ReservationDTO reservationDTO, Long userId);

    ResponseEntity<ReservationView> getReservationById(Long reservationId);

    Iterable<ReservationView> getAllUserReservations(Long userId);

    ResponseEntity deleteReservation(Long reservationId);

}
