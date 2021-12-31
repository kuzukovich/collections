package com.example.collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Employee not found";

    public EmployeeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}