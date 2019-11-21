package com.restaurant.controllers;

import com.restaurant.commands.request.ReservationDTO;
import com.restaurant.services.ReservationService;
import com.restaurant.views.ReservationView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER') or #userId == principal.id")
    public ResponseEntity<ReservationView> makeReservation(@RequestBody ReservationDTO reservationDTO, @PathVariable Long userId) {
        return reservationService.makeReservation(reservationDTO, userId);
    }

    @GetMapping("/{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER')")
    public ResponseEntity<ReservationView> getReservationById(@PathVariable Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER') or #userId == principal.id ")
    public Iterable<ReservationView> getAllUserReservations(@PathVariable Long userId) {
        return reservationService.getAllUserReservations(userId);
    }

    @DeleteMapping("/{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN','WAITER')")
    public ResponseEntity deleteReservationById(@PathVariable Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }

}
