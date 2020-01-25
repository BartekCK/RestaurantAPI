package com.restaurant.utility.exceptions;

public class OrderAlreadyAcceptedException extends RuntimeException {
    public OrderAlreadyAcceptedException() {
        super("Order is already accepted by chef");
    }
}
