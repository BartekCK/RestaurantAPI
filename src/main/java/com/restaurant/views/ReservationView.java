package com.restaurant.views;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationView {

    private Long reservationId;
    private LocalDateTime dateReservation;
    private String username;
    private Integer tableId;
    private Long orderId;

}
