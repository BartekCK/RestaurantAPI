package com.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
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

    @Size(max = 300)
    private String textOpinion;

}
