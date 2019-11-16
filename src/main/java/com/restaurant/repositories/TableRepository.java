package com.restaurant.repositories;

import com.restaurant.commands.TableCommand;
import com.restaurant.views.TableView;

import java.util.List;

public interface TableRepository {
    Long saveTable(TableCommand tableCommand);

    TableView updateTable(Long tableId, TableCommand tableCommand);

    TableView getTableById(Long tableId);

    List<TableView> getAllTables();

    List<TableView> getAllTablesByRestaurantId(Integer restaurantId);
}
