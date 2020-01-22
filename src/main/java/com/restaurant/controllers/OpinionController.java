package com.restaurant.controllers;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.services.OpinionService;
import com.restaurant.commands.response.OpinionView;
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
    public Long addOpinion(@RequestBody @Valid OpinionDTO opinionDTO) {
        return opinionService.addOpinion(opinionDTO);
    }

    @PutMapping("/{opinionId}")
    @PreAuthorize("hasRole('USER')")
    public OpinionView updateOpinion(@PathVariable Long opinionId, @RequestBody @Valid OpinionDTO opinionDTO) {
        return opinionService.updateOpinion(opinionId, opinionDTO);
    }

    @GetMapping("/{opinionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public OpinionView getOpinionById(@PathVariable Long opinionId) {
        return opinionService.getOpinionById(opinionId);
    }
}
