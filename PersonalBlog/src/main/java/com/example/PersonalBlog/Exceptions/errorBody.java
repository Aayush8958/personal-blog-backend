package com.example.PersonalBlog.Exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

public class errorBody {

    private final String message;
    private final Map<String,String> errors;
    private final  Throwable throwable;
    private final HttpStatus httpStatus;

    public Map<String, String> getErrors() {
        return errors;
    }

    public errorBody(String message, Throwable throwable, HttpStatus httpStatus, Map<String,String> errors) {
        this.message = message;
        this.errors =errors;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
