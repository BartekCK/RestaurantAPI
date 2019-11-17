package com.restaurant.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@javax.persistence.Table(name = "reservation")
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
