package com.restaurant.utility.exceptions;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(Long tableId) {
        super(String.format("Table %s not found", tableId));
    }
}
