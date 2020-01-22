package com.restaurant.repositories;

import com.restaurant.commands.request.TableDTO;
import com.restaurant.models.Restaurant;
import com.restaurant.models.Table;
import com.restaurant.repositories.jpa.RestaurantJPARepository;
import com.restaurant.repositories.jpa.TableJPARepository;
import com.restaurant.utility.exceptions.RestaurantNotFoundException;
import com.restaurant.utility.exceptions.TableNotFoundException;
import com.restaurant.utility.mappers.TableMapper;
import com.restaurant.commands.response.TableView;
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
    public Long saveTable(TableDTO tableDTO) {
        return tableJPARepository.save(buildTableFromCommand(tableDTO)).getTableId();
    }

    @Override
    public TableView updateTable(Long tableId, TableDTO tableDTO) {
        Table table = getTable(tableId);
        Table updatedTable = getUpdatedTable(tableDTO, table);
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
    public List<TableView> getAllTablesByRestaurantId(Integer restaurantId) {
        return tableJPARepository
                .findAll()
                .stream()
                .filter(table -> table.getRestaurant().getRestaurantId().equals(restaurantId))
                .map(TableMapper::mapTableToTableView)
                .collect(Collectors.toList());
    }

    private Table buildTableFromCommand(TableDTO tableDTO) {

        Restaurant restaurant = restaurantJPARepository.findById(tableDTO.getRestaurantId()).orElseThrow(() -> new RestaurantNotFoundException(tableDTO.getRestaurantId()));
        return Table.builder()
                .seatsNumber(tableDTO.getSeatsNumber())
                .restaurant(restaurant)
                .build();
    }

    private Table getTable(Long tableId) {
        return tableJPARepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException(tableId));
    }

    private Table getUpdatedTable(TableDTO tableDTO, Table table) {
        table.setSeatsNumber(tableDTO.getSeatsNumber());
        Restaurant restaurant = restaurantJPARepository.findById(tableDTO.getRestaurantId()).orElseThrow(() -> new RestaurantNotFoundException(tableDTO.getRestaurantId()));
        table.setRestaurant(restaurant);
        return table;
    }
}
