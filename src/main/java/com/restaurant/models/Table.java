package com.restaurant.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@javax.persistence.Table(name = "restaurants_table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @Column(nullable = false)
    private int seatsNumber;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "reservationId")
    private Set<Reservation> reservationSet;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Table) {
            Table table = (Table) o;
            return tableId.equals(table.tableId) && seatsNumber == table.seatsNumber;
        }
        return false;
    }
}
