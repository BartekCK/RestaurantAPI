package com.restaurant.controllers;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.commands.request.RestaurantDTO;
import com.restaurant.commands.response.OpinionView;
import com.restaurant.commands.response.RestaurantView;
import com.restaurant.commands.response.TableView;
import com.restaurant.services.OpinionService;
import com.restaurant.services.RestaurantService;
import com.restaurant.services.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final TableService tableService;
    private final OpinionService opinionService;

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
