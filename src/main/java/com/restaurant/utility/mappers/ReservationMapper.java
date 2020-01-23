package com.restaurant.utility.mappers;

import com.restaurant.commands.response.ReservationView;
import com.restaurant.models.Reservation;

public class ReservationMapper {
    public static ReservationView mapReservationToReservationView(Reservation reservation) {
        return ReservationView.builder()
                .reservationId(reservation.getReservationId())
                .dateReservation(reservation.getDateReservation())
                .tableId(reservation.getTable().getTableId())
                .comments(reservation.getComments())
                .build();
    }
}
