package com.restaurant.services;

import com.restaurant.commands.request.ReservationDTO;
import com.restaurant.repositories.ReservationRepository;
import com.restaurant.views.ReservationView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public ResponseEntity<ReservationView> makeReservation(ReservationDTO reservationDTO, Long userId) {
        return reservationRepository.createReservationForUser(reservationDTO,userId);
    }

    @Override
    public ResponseEntity<ReservationView> getReservationById(Long reservationId) {
        return reservationRepository.getReservationById(reservationId);
    }
}
