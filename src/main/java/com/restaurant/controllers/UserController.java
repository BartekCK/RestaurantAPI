package com.restaurant.controllers;

import com.restaurant.commands.request.UserDTO;
import com.restaurant.commands.response.OpinionView;
import com.restaurant.commands.response.UserPrincipal;
import com.restaurant.services.OpinionService;
import com.restaurant.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/index")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final OpinionService opinionService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/users/{userId}")
    //@PreAuthorize("hasRole('ADMIN') or #userId == principal.id")
    public UserPrincipal getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    //@PreAuthorize("hasRole('ADMIN')")
    public Iterable<UserPrincipal> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{userId}")
    //@PreAuthorize("#userId == principal.id")
    public ResponseEntity<UserPrincipal> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @DeleteMapping("/users/{userId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/user/opinions")
    //@PreAuthorize("hasRole('USER')")
    public List<OpinionView> getOpinionsByUserId(@PathVariable Long userId) {
        return opinionService.getAllOpinionsByUserId(userId);
    }
}
