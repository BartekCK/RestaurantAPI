package com.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@javax.persistence.Table(name = "reservation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private LocalDateTime dateReservation;

    @ManyToOne
    private Table table;

    @OneToOne
    private Order order;

    private String comments;

    public Reservation(LocalDateTime localDateTime) {
        this.dateReservation = localDateTime;
    }

}
