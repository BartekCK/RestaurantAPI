package com.restaurant.commands.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private Set<String> roles;

    private double salary;

}
