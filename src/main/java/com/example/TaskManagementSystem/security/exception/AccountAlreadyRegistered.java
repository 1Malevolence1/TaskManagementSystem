package com.example.TaskManagementSystem.security.exception;

import com.example.TaskManagementSystem.utils.exception.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AccountAlreadyRegistered extends RuntimeException {
    private final Error error;
}
