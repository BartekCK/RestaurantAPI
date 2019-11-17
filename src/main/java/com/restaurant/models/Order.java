package com.restaurant.models;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private double bill;

    @OneToOne
    private Reservation reservation;

    @ManyToOne
    private Employee employee;

    @ManyToMany
    @JoinTable(name = "order_menu")
    private List<Dish> orderDishes;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Order) {
            Order order = (Order) o;
            return orderId == order.orderId && bill == order.bill;
        }
        return false;
    }

}
