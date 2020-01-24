package com.restaurant.models;

import com.restaurant.models.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private double bill;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToMany
    @JoinTable(name = "order_menu")
    private List<Dish> orderDishes;

    @Column(name = "additional_remarks")
    private String additionalRemarks;

    @Enumerated
    private OrderStatusEnum status;

    @Column(name = "create_timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTimestamp;

    @Column(name = "payment_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentTimestamp;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Order) {
            Order order = (Order) o;
            return orderId.equals(order.orderId) && bill == order.bill;
        }
        return false;
    }

}
