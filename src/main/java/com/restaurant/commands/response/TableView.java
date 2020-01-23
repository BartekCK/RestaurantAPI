package com.restaurant.commands.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableView {
    private Long tableId;
    private int seatsNumber;
    private RestaurantView restaurant;

}
