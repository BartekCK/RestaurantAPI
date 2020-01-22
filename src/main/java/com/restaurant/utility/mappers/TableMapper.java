package com.restaurant.utility.mappers;

import com.restaurant.models.Table;
import com.restaurant.commands.response.TableView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TableMapper {
    public static TableView mapTableToTableView(Table table) {
        return TableView.builder()
                .tableId(table.getTableId())
                .seatsNumber(table.getSeatsNumber())
                .restaurant(RestaurantMapper.mapRestaurantToRestaurantView(table.getRestaurant()))
                .build();

    }
}
