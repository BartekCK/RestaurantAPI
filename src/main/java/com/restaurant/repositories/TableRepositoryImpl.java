package com.restaurant.repositories;

import com.restaurant.commands.TableCommand;
import com.restaurant.models.Restaurant;
import com.restaurant.models.Table;
import com.restaurant.repositories.jpa.RestaurantJPARepository;
import com.restaurant.repositories.jpa.TableJPARepository;
import com.restaurant.utility.exceptions.RestaurantNotFoundException;
import com.restaurant.utility.exceptions.TableNotFoundException;
import com.restaurant.utility.mappers.TableMapper;
import com.restaurant.views.TableView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TableRepositoryImpl implements TableRepository {

    private final TableJPARepository tableJPARepository;
    private final RestaurantJPARepository restaurantJPARepository;

    @Override
    public Long saveTable(TableCommand tableCommand) {
        return tableJPARepository.save(buildTableFromCommand(tableCommand)).getTableId();
    }

    @Override
    public TableView updateTable(Long tableId, TableCommand tableCommand) {
        Table table = getTable(tableId);
        Table updatedTable = getUpdatedTable(tableCommand, table);
        tableJPARepository.save(updatedTable);
        return TableMapper.mapTableToTableView(table);
    }

    @Override
    public TableView getTableById(Long tableId) {
        return tableJPARepository
                .findById(tableId)
                .map(TableMapper::mapTableToTableView)
                .orElseThrow(() -> new TableNotFoundException(tableId));
    }

    @Override
    public List<TableView> getAllTables() {
        return tableJPARepository
                .findAll()
                .stream()
                .map(TableMapper::mapTableToTableView)
                .collect(Collectors.toList());
    }

    @Override
    public List<TableView> getAllTablesByRestaurantId(Integer restaurantId){
        return tableJPARepository
                .findAll()
                .stream()
                .filter(table -> table.getRestaurant().getRestaurantId().equals(restaurantId))
                .map(TableMapper::mapTableToTableView)
                .collect(Collectors.toList());
    }

    private Table buildTableFromCommand(TableCommand tableCommand) {
        return Table.builder()
                .seatsNumber(tableCommand.getSeatsNumber())
                .build();
    }

    private Table getTable(Long tableId) {
        return tableJPARepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException(tableId));
    }

    private Table getUpdatedTable(TableCommand tableCommand, Table table) {
        table.setSeatsNumber(tableCommand.getSeatsNumber());
        Optional<Restaurant> restaurant = restaurantJPARepository.findById(tableCommand.getRestaurantId());
        if(!restaurant.isPresent())
            throw new RestaurantNotFoundException(tableCommand.getRestaurantId());
        table.setRestaurant(restaurant.get());
        return table;
    }
}
