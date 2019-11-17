package com.restaurant.controllers;

import com.restaurant.commands.OpinionCommand;
import com.restaurant.services.OpinionService;
import com.restaurant.views.OpinionView;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("opinion")
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Long addOpinion(@RequestBody @Valid OpinionCommand opinionCommand) {
        return opinionService.addOpinion(opinionCommand);
    }

    @PutMapping("/{opinionId}")
    @PreAuthorize("hasRole('USER')")
    public OpinionView updateOpinion(@PathVariable Long opinionId, @RequestBody @Valid OpinionCommand opinionCommand) {
        return opinionService.updateOpinion(opinionId, opinionCommand);
    }

    @GetMapping("/{opinionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public OpinionView getOpinionById(@PathVariable Long opinionId) {
        return opinionService.getOpinionById(opinionId);
    }
}
