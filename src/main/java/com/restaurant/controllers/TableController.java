package com.restaurant.controllers;

import com.restaurant.commands.request.TableDTO;
import com.restaurant.services.TableService;
import com.restaurant.commands.response.TableView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public Long addTable(@RequestBody @Valid TableDTO tableDTO) {
        return tableService.addTable(tableDTO);
    }

    @PutMapping("/{tableId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public TableView updateTable(@PathVariable Long tableId, @RequestBody @Valid TableDTO tableDTO) {
        return tableService.updateTable(tableId, tableDTO);
    }

    @GetMapping("/{tableId}")
   //@PreAuthorize("hasRole('ADMIN')")
    public TableView getTableById(@PathVariable Long tableId) {
        return tableService.getTableById(tableId);
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<TableView> getTables() {
        return tableService.getAllTables();
    }
}
