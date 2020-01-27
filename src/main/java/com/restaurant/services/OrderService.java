package com.restaurant.services;

import com.restaurant.commands.request.OrderDTO;
import com.restaurant.commands.response.OrderView;
import com.restaurant.models.*;
import com.restaurant.models.authority.RoleName;
import com.restaurant.models.enums.OrderStatusEnum;
import com.restaurant.repositories.jpa.*;
import com.restaurant.utility.EmployeeCannotChangeStatusException;
import com.restaurant.utility.exceptions.*;
import com.restaurant.utility.mappers.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class OrderService {

    public static final int MINUTES_PARAMETER = 2;
    private final OrderJPARepository orderRepository;
    private final ReservationJPARepository reservationRepository;
    private final UserJPARepository userRepository;
    private final EmployeeJPARepository employeeRepository;
    private final DishJPARepository dishRepository;

    public Long saveOrder(OrderDTO orderDTO) {
        Order order = buildOrder(orderDTO);
        return orderRepository.save(order).getOrderId();
    }

    public Long updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = getOrder(orderId);
        if (order.getStatus().equals(OrderStatusEnum.ACCEPTED_BY_CHEF))
            throw new OrderAlreadyAcceptedException();
        Order updatedOrder = buildUpdatedOrder(orderId, orderDTO);
        return orderRepository.save(updatedOrder).getOrderId();

    }

    private Order getOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent())
            throw new OrderNotFoundException();
        return orderOptional.get();
    }

    public OrderView getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent())
            throw new OrderNotFoundException();
        return OrderMapper.mapOrderToOrderView(order.get());
    }

    public List<OrderView> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper::mapOrderToOrderView)
                .collect(Collectors.toList());
    }

    public OrderStatusEnum changeOrderStatus(Long orderId, Long employeeId, OrderStatusEnum status) {
        Order order = getOrder(orderId);
        Employee employee = getEmployee(employeeId);
        if (!(employee.getUserId().getRoles().stream().anyMatch(role -> role.getName().equals(RoleName.ROLE_COOK))
                && (status.equals(OrderStatusEnum.ACCEPTED_BY_CHEF)
                || status.equals(OrderStatusEnum.READY_TO_GO)))
        ) throw new EmployeeCannotChangeStatusException();
        if (!(employee.getUserId().getRoles().stream().anyMatch(role -> role.getName().equals(RoleName.ROLE_WAITER))
                && (status.equals(OrderStatusEnum.DELIVERED)
                || status.equals(OrderStatusEnum.PAID)))
        ) throw new EmployeeCannotChangeStatusException();
        if (status.equals(OrderStatusEnum.PAID))
            order.setPaymentTimestamp(Calendar.getInstance().getTime());
        order.setStatus(status);
        orderRepository.save(order);
        return order.getStatus();
    }

    public int getRemainingTimeInMinutes(Long orderId) {
        Order order = getOrder(orderId);
        int biggestTimeForOrder = order.getOrderDishes().stream()
                .map(Dish::getCookingTime)
                .max(Integer::compare)
                .get();
        int orderCount = (int)orderRepository.findAll().stream()
                .filter(order1 -> order1.getStatus().equals(OrderStatusEnum.ACCEPTED_BY_CHEF) || order1.getStatus().equals(OrderStatusEnum.PLACED))
                .count();
        return biggestTimeForOrder + MINUTES_PARAMETER * orderCount;
    }

    private Reservation getReservation(OrderDTO orderDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(orderDTO.getReservationId());
        if (!reservationOptional.isPresent())
            throw new ReservationNotFoundException();
        return reservationOptional.get();
    }

    private User getCustomer(OrderDTO orderDTO) {
        Optional<User> userOptional = userRepository.findById(orderDTO.getCustomerId());
        if (!userOptional.isPresent())
            throw new UserNotFoundException(orderDTO.getCustomerId());
        return userOptional.get();
    }

    private Employee getEmployee(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent())
            throw new EmployeeNotFoundException();
        return employeeOptional.get();
    }

    private List<Dish> getDishes(OrderDTO orderDTO) {
        List<Dish> dishes = orderDTO.getDishesIds().stream()
                .map(dishRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        if (dishes.isEmpty())
            throw new DishesMustNotBeEmptyException();
        return dishes;
    }

    private double calculateBill(List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getGrossPrice)
                .mapToDouble(Double::doubleValue).sum();
    }

    private Order buildOrder(OrderDTO orderDTO) {
        Reservation reservation = getReservation(orderDTO);
        User user = getCustomer(orderDTO);
        Employee employee = getEmployee(orderDTO.getEmployeeId());
        List<Dish> dishes = getDishes(orderDTO);
        double bill = calculateBill(dishes);
        return Order.builder()
                .reservation(reservation)
                .employee(employee)
                .user(user)
                .orderDishes(dishes)
                .bill(bill)
                .status(OrderStatusEnum.PLACED)
                .additionalRemarks(orderDTO.getAdditionalRemarks())
                .orderTimestamp(Calendar.getInstance().getTime())
                .build();
    }

    private Order buildUpdatedOrder(Long orderId, OrderDTO orderDTO) {
        Reservation reservation = getReservation(orderDTO);
        User user = getCustomer(orderDTO);
        Employee employee = getEmployee(orderDTO.getEmployeeId());
        List<Dish> dishes = getDishes(orderDTO);
        double bill = calculateBill(dishes);
        return Order.builder()
                .orderId(orderId)
                .reservation(reservation)
                .employee(employee)
                .user(user)
                .orderDishes(dishes)
                .bill(bill)
                .status(OrderStatusEnum.PLACED)
                .additionalRemarks(orderDTO.getAdditionalRemarks())
                .orderTimestamp(Calendar.getInstance().getTime())
                .build();
    }
}
