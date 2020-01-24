package com.restaurant.utility.exceptions;

import static com.restaurant.utility.constants.ExceptionMessages.TABLE_NOT_FOUND;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(Long tableId) {
        super(String.format(TABLE_NOT_FOUND, tableId));
    }

    public TableNotFoundException() {
        super(TABLE_NOT_FOUND);
    }
}
