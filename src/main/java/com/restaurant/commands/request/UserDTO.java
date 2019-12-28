package com.restaurant.commands.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDTO {
    private String username;
    @Email
    private String email;
    private String phone;
    private String password;
}
