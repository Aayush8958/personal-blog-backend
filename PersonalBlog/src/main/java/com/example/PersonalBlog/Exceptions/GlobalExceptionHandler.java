package com.example.PersonalBlog.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler (value = {NotFound.class})
    public ResponseEntity<errorBody> HandleNotFound(NotFound notFound){
        errorBody errorbody=new errorBody(
                notFound.getMessage(),notFound.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(errorbody,HttpStatus.NOT_FOUND);
    }
}
