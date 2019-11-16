package com.restaurant.controllers;

import com.restaurant.commands.TableCommand;
import com.restaurant.services.TableService;
import com.restaurant.views.TableView;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("table")
public class TableController {

    private final TableService tableService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Long addTable(@RequestBody @Valid TableCommand tableCommand) {
        return tableService.addTable(tableCommand);
    }

    @PutMapping("/{tableId}")
    @PreAuthorize("hasRole('ADMIN')")
    public TableView updateTable(@PathVariable Long tableId, @RequestBody @Valid TableCommand tableCommand) {
        return tableService.updateTable(tableId, tableCommand);
    }

    @GetMapping("/{tableId}")
    @PreAuthorize("hasRole('ADMIN')")
    public TableView getTableById(@PathVariable Long tableId) {
        return tableService.getTableById(tableId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TableView> getTables() {
        return tableService.getAllTables();
    }

    @GetMapping
    public List<TableView> getTablesByRestaurantId(Integer restaurantId) {
        return tableService.getAllTablesByRestaurantId(restaurantId);
    }
}
