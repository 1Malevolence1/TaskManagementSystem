package com.example.TaskManagementSystem.security.exception;

import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadRequestSingInCustomer extends RuntimeException {
    private final Error error;
}
