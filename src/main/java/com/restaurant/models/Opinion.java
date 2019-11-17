package com.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static com.restaurant.utility.constants.ExceptionMessages.OPINION_SIZE_MESSAGE;

@Entity
@Builder
@AllArgsConstructor
@Data
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opinionId;

    @OneToOne
    private User customer;

    @OneToOne
    private Restaurant restaurant;

    @Size(max = 300, message = OPINION_SIZE_MESSAGE)
    private String textOpinion;

}
