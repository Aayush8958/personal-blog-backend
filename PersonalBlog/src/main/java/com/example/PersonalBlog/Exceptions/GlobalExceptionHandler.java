package com.example.PersonalBlog.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler (value = {NotFound.class})
    public ResponseEntity<errorBody> HandleNotFound(NotFound notFound){
        errorBody errorbody=new errorBody(
                notFound.getMessage(),notFound.getCause(),
                HttpStatus.NOT_FOUND,null
        );

        return new ResponseEntity<>(errorbody,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<errorBody> HandleInvalidData(MethodArgumentNotValidException InvalidData){
        Map<String,String> errors=new HashMap<>();
        for(FieldError error: InvalidData.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        errorBody errorbody=new errorBody(
                "Validation error",
                InvalidData.getCause(),
                HttpStatus.BAD_REQUEST,
                errors
        );

        return new ResponseEntity<>(errorbody,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<errorBody> HandleInvalidDataType(MethodArgumentTypeMismatchException InvalidDataType){
        errorBody errorbody=new errorBody(
                "Invalid Data Type used",
                null,
                HttpStatus.BAD_REQUEST,
                null

        );

        return new ResponseEntity<>(errorbody,HttpStatus.BAD_REQUEST);
}
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<errorBody> HandleInvalidDataTypeInJSON(MethodArgumentTypeMismatchException InvalidDataType){
        errorBody errorbody=new errorBody(
                "Invalid Data Type used,Expecting Integer",
                null,
                HttpStatus.BAD_REQUEST,
                null

        );

        return new ResponseEntity<>(errorbody,HttpStatus.BAD_REQUEST);
    }
}
