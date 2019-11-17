package com.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @OneToOne
    private User userId;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Column(nullable = false)
    private double salary;

    @OneToMany(mappedBy = "orderId")
    private Set<Order> orderSet;


}
