package com.restaurant.services;

import com.restaurant.commands.TableCommand;
import com.restaurant.repositories.TableRepository;
import com.restaurant.views.TableView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    @Override
    public Long addTable(TableCommand tableCommand) {
        return tableRepository.saveTable(tableCommand);
    }

    @Override
    public TableView updateTable(Long tableId, TableCommand tableCommand) {
        return tableRepository.updateTable(tableId, tableCommand);
    }

    @Override
    public TableView getTableById(Long tableId) {
        return tableRepository.getTableById(tableId);
    }

    @Override
    public List<TableView> getAllTables() {
        return tableRepository.getAllTables();
    }

    @Override
    public List<TableView> getAllTablesByRestaurantId(Integer restaurantId) {
        return tableRepository.getAllTablesByRestaurantId(restaurantId);
    }
}
