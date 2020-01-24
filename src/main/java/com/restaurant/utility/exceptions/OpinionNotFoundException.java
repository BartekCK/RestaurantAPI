package com.restaurant.utility.exceptions;

import static com.restaurant.utility.constants.ExceptionMessages.OPINION_NOT_FOUND;

public class OpinionNotFoundException extends RuntimeException {
    public OpinionNotFoundException(Long opinionId) {
        super(String.format(OPINION_NOT_FOUND, opinionId));
    }

    public OpinionNotFoundException(String message) {
        super(message);
    }
}
