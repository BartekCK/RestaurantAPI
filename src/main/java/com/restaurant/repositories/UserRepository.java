package com.restaurant.repositories;

import com.restaurant.commands.request.UserDTO;
import com.restaurant.views.UserPrincipal;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository {

    ResponseEntity<String> saveUser(UserDTO userDTO);

    @Transactional
    ResponseEntity deleteUser(Long userId);

    ResponseEntity<UserPrincipal> updateUser(Long userId, UserDTO userDTO);

    UserPrincipal getUserById(Long userId);

    List<UserPrincipal> getAllUsers();

    @Transactional
    UserPrincipal findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
