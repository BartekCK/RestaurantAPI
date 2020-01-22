package com.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private LocalDateTime dateReservation;

    @ManyToOne
    @JoinColumn(name="table_id", nullable=false)
    private Table table;

    @OneToMany(mappedBy = "reservation")
    private List<Order> order;

    private String comments;

    public Reservation(LocalDateTime localDateTime) {
        this.dateReservation = localDateTime;
    }

}
