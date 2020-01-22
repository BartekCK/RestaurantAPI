package com.restaurant.services;

import com.restaurant.commands.request.TableDTO;
import com.restaurant.repositories.TableRepository;
import com.restaurant.commands.response.TableView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    @Override
    public Long addTable(TableDTO tableDTO) {
        return tableRepository.saveTable(tableDTO);
    }

    @Override
    public TableView updateTable(Long tableId, TableDTO tableDTO) {
        return tableRepository.updateTable(tableId, tableDTO);
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
