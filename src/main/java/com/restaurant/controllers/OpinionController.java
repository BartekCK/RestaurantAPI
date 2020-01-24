package com.restaurant.controllers;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.commands.response.OpinionView;
import com.restaurant.services.OpinionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/opinions")
public class OpinionController {

    private final OpinionService opinionService;

    @PutMapping("/{opinionId}")//without UserID
    //@PreAuthorize("hasRole('USER')")
    public OpinionView updateOpinion(@PathVariable Long opinionId, @RequestBody @Valid OpinionDTO opinionDTO) {
        return opinionService.updateOpinion(opinionId, opinionDTO);
    }

    @GetMapping("{opinionId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public OpinionView getOpinionById(@PathVariable Long opinionId) {
        return opinionService.getOpinionById(opinionId);
    }

//    @GetMapping("user/{userId}")// In User Controller
//    //@PreAuthorize("hasRole('ADMIN')")
//    public List<OpinionView> getOpinionsByUserId(@PathVariable Long userId) {
//        return opinionService.getAllOpinionsByUserId(userId);
//    }
//
//    @GetMapping("/restaurant/{restaurantId}")//In Restaurant Controller
//    //@PreAuthorize("hasRole('ADMIN')")
//    public List<OpinionView> getOpinionsByRestaurantId(@PathVariable Integer restaurantId) {
//        return opinionService.getAllOpinionsByRestaurantId(restaurantId);
//    }
}
