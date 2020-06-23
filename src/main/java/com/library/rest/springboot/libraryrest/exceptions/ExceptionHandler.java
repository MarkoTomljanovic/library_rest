package com.library.rest.springboot.libraryrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    //handler for the 404 error
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(CustomException e){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //handler for the 400 error (all other errors except 404)
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
