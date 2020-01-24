package com.restaurant.controllers;

import com.restaurant.commands.request.OpinionDTO;
import com.restaurant.commands.request.ReservationDTO;
import com.restaurant.commands.request.UserDTO;
import com.restaurant.commands.response.OpinionView;
import com.restaurant.commands.response.ReservationView;
import com.restaurant.commands.response.UserPrincipal;
import com.restaurant.services.OpinionService;
import com.restaurant.services.ReservationService;
import com.restaurant.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final OpinionService opinionService;
    private final ReservationService reservationService;


    @PostMapping("signup")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("{userId}")
    //@PreAuthorize("hasRole('ADMIN') or #userId == principal.id")
    public UserPrincipal getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }


    @PutMapping("{userId}")
    //@PreAuthorize("#userId == principal.id")
    public ResponseEntity<UserPrincipal> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @DeleteMapping("{userId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("{userId}/opinions/{restaurantId}")
    //@PreAuthorize("hasRole('USER')")
    public Long addOpinion(@PathVariable Long userId, @PathVariable Integer restaurantId, @RequestBody OpinionDTO opinionDTO) {
        return opinionService.addOpinion(userId, restaurantId, opinionDTO);
    }

    @PutMapping("opinions/{opinionId}")//without UserID
    //@PreAuthorize("hasRole('USER')")
    public OpinionView updateOpinion(@PathVariable Long opinionId, @RequestBody @Valid OpinionDTO opinionDTO) {
        return opinionService.updateOpinion(opinionId, opinionDTO);
    }

    @GetMapping("{userId}/opinions")
    //@PreAuthorize("hasRole('USER')")
    public List<OpinionView> getAllOpinionsByUserId(@PathVariable Long userId) {
        return opinionService.getAllOpinionsByUserId(userId);
    }

    @PostMapping("{userId}/reservations")
    //@PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER') or #userId == principal.id")
    public ResponseEntity<ReservationView> makeReservation(@RequestBody ReservationDTO reservationDTO, @PathVariable Long userId) {
        return reservationService.makeReservation(reservationDTO, userId);
    }

    @GetMapping("{userId}/reservations")
    //@PreAuthorize("hasAnyRole('ADMIN','COOK','WAITER') or #userId == principal.id ")
    public Iterable<ReservationView> getAllUserReservations(@PathVariable Long userId) {
        return reservationService.getAllUserReservations(userId);
    }

}
