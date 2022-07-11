package com.masai.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setTimestamp(LocalDateTime.now());
        details.setMessage(exception.getMessage());
        details.setDetails(request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ErrorDetails> handleInvalidTransactionException(InvalidTransactionException exception, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setTimestamp(LocalDateTime.now());
        details.setMessage(exception.getMessage());
        details.setDetails(request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setTimestamp(LocalDateTime.now());
        details.setMessage(exception.getMessage());
        details.setDetails(request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
