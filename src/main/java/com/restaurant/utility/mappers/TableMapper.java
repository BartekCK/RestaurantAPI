package com.restaurant.utility.mappers;

import com.restaurant.models.Table;
import com.restaurant.views.TableView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TableMapper {
    public static TableView mapTableToTableView(Table table) {
        return TableView.builder()
                .tableId(table.getTableId())
                .seatsNumber(table.getSeatsNumber())
                .restaurant(RestaurantMapper.mapRestaurantToRestaurantView(table.getRestaurant()))
                .reservationSet(table.getReservationSet())
                .build();
    }
}
