package com.example.TaskManagementSystem.utils.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProblemDetailsErrors {
    List<ErrorProblemDetails> errors;
}
