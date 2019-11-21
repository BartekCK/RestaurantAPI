package com.restaurant.utility.mappers;

import com.restaurant.models.Reservation;
import com.restaurant.views.ReservationView;

public class ReservationMapper {
    public static ReservationView mapReservationToReservationView(Reservation reservation) {
        return ReservationView.builder()
                .reservationId(reservation.getReservationId())
                .dateReservation(reservation.getDateReservation())
                .username(reservation.getCustomer().getUsername())
                .tableId(reservation.getTable().getTableId())
                //without order
                .build();
    }
}
