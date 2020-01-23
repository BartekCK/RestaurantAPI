package com.restaurant.services;

import com.restaurant.commands.request.TableDTO;
import com.restaurant.commands.response.TableView;

import java.util.List;

public interface TableService {
    Long addTable(TableDTO tableDTO);

    TableView updateTable(Long tableId, TableDTO tableDTO);

    TableView getTableById(Long tableId);

    List<TableView> getAllTables();

    List<TableView> getAllTablesByRestaurantId(Integer restaurantId);
}
