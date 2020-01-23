package com.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @Column(nullable = false)
    private int seatsNumber;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table")
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

    @Override
    public String toString() {
        return "Table{" +
                "tableId=" + tableId +
                ", seatsNumber=" + seatsNumber +
                //", restaurant=" + restaurant +
                ", reservationSet=" + reservationSet +
                '}';
    }
}
