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
public class Reservation implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private LocalDateTime dateReservation;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Table table;

    @OneToOne
    private Order order;

    public Reservation(LocalDateTime localDateTime) {
        this.dateReservation = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Reservation) {
            Reservation reservation = (Reservation) o;
            if (dateReservation.isEqual(reservation.dateReservation))
                return 0;
            else if (dateReservation.isAfter(reservation.dateReservation))
                return 1;
            else if (dateReservation.isBefore(reservation.dateReservation))
                return -1;
        }
        return -1;
    }
}
