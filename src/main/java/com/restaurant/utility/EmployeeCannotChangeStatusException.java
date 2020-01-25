package com.restaurant.utility;

public class EmployeeCannotChangeStatusException extends RuntimeException {
    public EmployeeCannotChangeStatusException(){
        super("Employee doesn't have permission to change for this status");
    }
}
