package com.restaurant.controllers;

import com.restaurant.commands.response.ReservationView;
import com.restaurant.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER')")
    public ResponseEntity<ReservationView> getReservationById(@PathVariable Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @DeleteMapping("{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN','WAITER')")
    public ResponseEntity deleteReservationById(@PathVariable Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }

}
