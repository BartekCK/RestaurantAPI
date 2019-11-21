package com.restaurant.controllers;

import com.restaurant.commands.RestaurantCommand;
import com.restaurant.services.RestaurantService;
import com.restaurant.views.RestaurantView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
  //  @PreAuthorize("hasRole('ADMIN')")
    public Integer addRestaurant(@RequestBody @Valid RestaurantCommand restaurantCommand) {
        return restaurantService.addRestaurant(restaurantCommand);
    }

    @PutMapping("/{restaurantId}")
   //@PreAuthorize("hasRole('ADMIN')")
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
}
