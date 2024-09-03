package com.example.guest_flow.config;

import com.example.guest_flow.dto.dto.general.ErrorResponseDTO;
import domain.attendees.exceptions.AttendeeAlreadyExistsException;
import domain.attendees.exceptions.AttendeesNotFoundException;
import domain.attendees.exceptions.EventFullExeption;
import domain.checkin.exception.CheckInAlreadyExistsException;
import domain.exceptions.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ExceptionEntityHaandler {

    @ExceptionHandler(domain.exceptions.EventNotFoundException.class)
    public ResponseEntity handleEventNotFound(EventNotFoundException exception ){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeesNotFoundException.class)
    public ResponseEntity handleAttendeeNotFound(AttendeesNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistsException.class)
    public ResponseEntity handleAttendeesAlreadyExistException
            (AttendeeAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handleCheckInAlreadyExistsException
            (CheckInAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(EventFullExeption.class)
    public ResponseEntity handleEventFullExeption(EventFullExeption exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }
}

