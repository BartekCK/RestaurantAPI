package com.restaurant.controllers;

import com.restaurant.commands.request.RestaurantDTO;
import com.restaurant.services.OpinionService;
import com.restaurant.services.RestaurantService;
import com.restaurant.services.TableService;
import com.restaurant.commands.response.OpinionView;
import com.restaurant.commands.response.RestaurantView;
import com.restaurant.commands.response.TableView;
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
    //  @PreAuthorize("hasRole('ADMIN')")
    public Integer addRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PutMapping("/{restaurantId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public RestaurantView updateRestaurant(@PathVariable Integer restaurantId, @RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(restaurantId, restaurantDTO);
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
