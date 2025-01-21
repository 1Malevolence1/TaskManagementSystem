package com.example.TaskManagementSystem.utils;


import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@Component
public class BindingResultValidate {

    @SneakyThrows
    public void check(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
    }
}
