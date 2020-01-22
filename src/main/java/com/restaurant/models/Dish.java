package com.restaurant.models;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    @Column(nullable = false)
    private String dishName;

    @Column(nullable = false)
    private String dishDescription;

    @Column(nullable = false)
    private double grossPrice;

    @Column(nullable = false)
    private int cookingTime;
}
