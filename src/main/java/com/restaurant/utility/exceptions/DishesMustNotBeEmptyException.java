package com.restaurant.utility.exceptions;

public class DishesMustNotBeEmptyException extends RuntimeException {
    public DishesMustNotBeEmptyException() {
        super("Dish list is empty");
    }
}
