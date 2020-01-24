package com.restaurant.utility.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ExceptionMessage> sendExceptionMessage(Exception e, HttpStatus httpStatus, String url) {
        log.error(e.getMessage());
        return ResponseEntity.status(httpStatus).body(new ExceptionMessage(url, e.getMessage()));
    }

    @ExceptionHandler({DishNotFoundException.class, OpinionNotFoundException.class,
            RestaurantNotFoundException.class, TableNotFoundException.class,
            UserNotFoundException.class, ReservationNotFoundException.class,
            EmployeeNotFoundException.class})
    @ResponseBody
    ResponseEntity<ExceptionMessage> handle404Error(HttpServletRequest request, Exception e) {
        return sendExceptionMessage(e, HttpStatus.NOT_FOUND, request.getRequestURL().toString());
    }
}
