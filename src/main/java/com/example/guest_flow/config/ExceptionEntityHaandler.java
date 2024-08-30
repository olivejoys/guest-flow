package com.example.guest_flow.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.w3c.dom.events.EventException;

@ControllerAdvice

public class ExceptionEntityHaandler {
    @ExceptionHandler(EventException.class)
    public ResponseEntity handlleEventNotFound(EventNotFoundException exception ){
        return ResponseEntity.notFound().build();
    }
}

