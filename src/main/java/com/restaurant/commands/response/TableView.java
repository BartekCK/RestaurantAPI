package com.restaurant.commands.response;

import com.restaurant.models.Reservation;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class TableView {
    private Long tableId;
    private int seatsNumber;
    private RestaurantView restaurant;

}
