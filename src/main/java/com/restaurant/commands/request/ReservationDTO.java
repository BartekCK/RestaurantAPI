package com.restaurant.commands.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ReservationDTO {

    @NotNull(message = "Reservation date error")
    private LocalDateTime dateReservation;

    @Max(value = 300, message = "Comment is to long")
    private String comments;

    @NotNull(message = "Choose table")
    private Long tableId;

}
