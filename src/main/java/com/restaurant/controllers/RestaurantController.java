package com.restaurant.controllers;

import com.restaurant.commands.RestaurantCommand;
import com.restaurant.services.OpinionService;
import com.restaurant.services.RestaurantService;
import com.restaurant.services.TableService;
import com.restaurant.views.OpinionView;
import com.restaurant.views.RestaurantView;
import com.restaurant.views.TableView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final TableService tableService;
    private final OpinionService opinionService;

    @PostMapping
    public Integer addRestaurant(@RequestBody @Valid RestaurantCommand restaurantCommand) {
        return restaurantService.addRestaurant(restaurantCommand);
    }

    @PutMapping("/{restaurantId}")
    public RestaurantView updateRestaurant(@PathVariable Integer restaurantId, @RequestBody RestaurantCommand restaurantCommand) {
        return restaurantService.updateRestaurant(restaurantId, restaurantCommand);
    }

    @GetMapping("/{restaurantId}")
    public RestaurantView getRestaurantById(@PathVariable Integer restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @GetMapping
    public List<RestaurantView> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("{restaurantId}/tables")
    public List<TableView> getTablesByRestaurantId(@PathVariable Integer restaurantId) {
        return tableService.getAllTablesByRestaurantId(restaurantId);
    }

    @GetMapping("{restaurantId}/opinions")
    public List<OpinionView> getOpinionsByRestaurantId(@PathVariable Integer restaurantId) {
        return opinionService.getAllOpinionsByRestaurantId(restaurantId);
    }
}
