package com.example.transactiondemo.controller;

import com.example.transactiondemo.dto.response.Response;
import com.example.transactiondemo.exception.DuplicateEntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DuplicateEntityException.class})
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                new Response<>("400", HttpStatus.BAD_REQUEST.toString(), ex.getLocalizedMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}
