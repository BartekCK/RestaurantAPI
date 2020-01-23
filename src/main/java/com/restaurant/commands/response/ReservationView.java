package com.restaurant.commands.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationView {

    private Long reservationId;
    private LocalDateTime dateReservation;
    private String comments;
    private Long tableId;
}
