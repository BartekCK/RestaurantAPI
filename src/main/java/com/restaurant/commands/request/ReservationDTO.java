package com.restaurant.commands.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ReservationDTO {

    private LocalDateTime dateReservation;
    private String comments;
    private Long tableId;
    private Long orderId;
}
