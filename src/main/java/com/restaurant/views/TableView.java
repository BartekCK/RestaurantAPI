package com.restaurant.views;

import com.restaurant.models.Reservation;
import lombok.Builder;

import java.util.Set;

@Builder
public class TableView {
    private Long tableId;
    private int seatsNumber;
    private RestaurantView restaurant;
    private Set<Reservation> reservationSet;
}
