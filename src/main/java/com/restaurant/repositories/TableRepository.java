package com.restaurant.repositories;

import com.restaurant.commands.request.TableDTO;
import com.restaurant.commands.response.TableView;

import java.util.List;

public interface TableRepository {
    Long saveTable(TableDTO tableDTO);

    TableView updateTable(Long tableId, TableDTO tableDTO);

    TableView getTableById(Long tableId);

    List<TableView> getAllTables();

    List<TableView> getAllTablesByRestaurantId(Integer restaurantId);
}
