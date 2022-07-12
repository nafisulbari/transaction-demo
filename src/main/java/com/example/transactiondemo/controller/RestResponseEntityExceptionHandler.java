package com.example.transactiondemo.controller;

import com.example.transactiondemo.dto.response.Response;
import com.example.transactiondemo.exception.DuplicateEntityException;
import com.example.transactiondemo.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DuplicateEntityException.class})
    public ResponseEntity<Object> handleDuplicateEntityException(Exception e) {
        return new ResponseEntity<Object>(
                new Response<>("55", e.getLocalizedMessage(), null),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(Exception e) {
        return new ResponseEntity<Object>(
                new Response<>("40", e.getLocalizedMessage(), null),
                new HttpHeaders(),
                HttpStatus.OK);
    }
}