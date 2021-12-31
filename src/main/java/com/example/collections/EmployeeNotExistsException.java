package com.example.collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotExistsException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Employee is not exist";

    public EmployeeNotExistsException() {
        super(DEFAULT_MESSAGE);
    }
}