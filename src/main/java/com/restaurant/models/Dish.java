package com.restaurant.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    @NotBlank
    private String dishName;

    @NotBlank
    private String dishDescription;

    @NonNull
    private double grossPrice;

    @NonNull
    private int cookingTime;
}
