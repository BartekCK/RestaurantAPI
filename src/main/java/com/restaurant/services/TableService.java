package com.restaurant.services;

import com.restaurant.commands.TableCommand;
import com.restaurant.views.TableView;

import java.util.List;

public interface TableService {
    Long addTable(TableCommand tableCommand);

    TableView updateTable(Long tableId, TableCommand tableCommand);

    TableView getTableById(Long tableId);

    List<TableView> getAllTables();

    List<TableView> getAllTablesByRestaurantId(Integer restaurantId);
}
