package com.example.TaskManagementSystem.utils.exception;


import lombok.Getter;

@Getter
public class ErrorProblemDetails extends Error {

    private final String field;

    public ErrorProblemDetails(String text, String field) {
        super(text);
        this.field = field;
    }
}
