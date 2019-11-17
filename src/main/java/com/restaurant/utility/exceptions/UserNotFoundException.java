package com.restaurant.utility.exceptions;

import static com.restaurant.utility.constants.ExceptionMessages.USER_NOT_FOUND;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super(String.format(USER_NOT_FOUND, userId));
    }
}
