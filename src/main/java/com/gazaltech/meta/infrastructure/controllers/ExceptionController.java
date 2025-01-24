package com.gazaltech.meta.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gazaltech.meta.shared.exceptions.BadRequestException;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleMaxAddressLimitException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleMaxAddressLimitException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
