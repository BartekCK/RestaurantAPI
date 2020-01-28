package com.restaurant.controllers;

import com.restaurant.commands.response.TableView;
import com.restaurant.services.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/tables")
public class TableController {

    private final TableService tableService;

    @GetMapping("{tableId}")
    public TableView getTableById(@PathVariable Long tableId) {
        return tableService.getTableById(tableId);
    }

    @GetMapping
    public List<TableView> getTables() {
        return tableService.getAllTables();
    }
}
