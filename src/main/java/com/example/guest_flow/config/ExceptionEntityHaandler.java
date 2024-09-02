package com.example.guest_flow.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ExceptionEntityHaandler {

    @ExceptionHandler(domain.exceptions.EventNotFoundException.class)
    public ResponseEntity handlleEventNotFound(EventNotFoundException exception ){
        return ResponseEntity.notFound().build();
    }
}

