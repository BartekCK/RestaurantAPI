package com.restaurant.repositories;

import com.restaurant.commands.request.OrderDTO;
import com.restaurant.commands.response.OrderView;
import com.restaurant.models.*;
import com.restaurant.models.enums.OrderStatusEnum;
import com.restaurant.repositories.jpa.*;
import com.restaurant.utility.exceptions.*;
import com.restaurant.utility.mappers.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderService {

    private final OrderJPARepository orderRepository;
    private final ReservationJPARepository reservationRepository;
    private final UserJPARepository userRepository;
    private final EmployeeJPARepository employeeRepository;
    private final DishJPARepository dishRepository;

    public Long saveOrder(OrderDTO orderDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(orderDTO.getReservationId());
        if (!reservationOptional.isPresent())
            throw new ReservationNotFoundException();
        Reservation reservation = reservationOptional.get();
        Optional<User> userOptional = userRepository.findById(orderDTO.getCustomerId());
        if (!userOptional.isPresent())
            throw new UserNotFoundException(orderDTO.getCustomerId());
        User user = userOptional.get();
        Optional<Employee> employeeOptional = employeeRepository.findById(orderDTO.getEmployeeId());
        if (!employeeOptional.isPresent())
            throw new EmployeeNotFoundException();
        Employee employee = employeeOptional.get();
        List<Dish> dishes = dishRepository.findAllById(orderDTO.getDishesIds());
        if (dishes.isEmpty())
            throw new DishesMustNotBeEmptyException();
        double bill = dishes.stream()
                .map(Dish::getGrossPrice)
                .mapToDouble(Double::doubleValue).sum();
        Order order = Order.builder()
                .reservation(reservation)
                .employee(employee)
                .user(user)
                .orderDishes(dishes)
                .bill(bill)
                .status(OrderStatusEnum.PLACED)
                .additionalRemarks(orderDTO.getAdditionalRemarks())
                .orderTimestamp(Calendar.getInstance().getTime())
                .build();
        return orderRepository.save(order).getOrderId();
    }

    public OrderView getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent())
            throw new OrderNotFoundException();
        return OrderMapper.mapOrderToOrderView(order.get());
    }
}
