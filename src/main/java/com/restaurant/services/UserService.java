package com.restaurant.services;

import com.restaurant.commands.request.UserDTO;
import com.restaurant.views.UserPrincipal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<String> createUser(UserDTO userDTO);

    ResponseEntity deleteUser(Long userId);

    ResponseEntity<UserPrincipal> updateUser(Long userId, UserDTO userDTO);

    UserPrincipal getUserById(Long userId);

    List<UserPrincipal> getAllUsers();

}
