package com.restaurant.commands.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value
public class OpinionDTO {

    @NotNull
    private String textOpinion;

    private LocalDateTime opinionDate;

    @JsonCreator
    public OpinionDTO(@JsonProperty(value = "textOpinion", required = true) String textOpinion) {
        this.textOpinion = textOpinion;
        opinionDate = LocalDateTime.now();
    }
}
