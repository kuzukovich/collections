package com.example.collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class EmployeeInvalidException extends IllegalArgumentException {
    public static final String DEFAULT_MESSAGE = "Employee is not valid";

    public EmployeeInvalidException() {
        super(DEFAULT_MESSAGE);
    }
}
