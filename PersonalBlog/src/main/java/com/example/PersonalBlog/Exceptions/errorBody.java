package com.example.PersonalBlog.Exceptions;

import org.springframework.http.HttpStatus;

public class errorBody {

    private final String message;
    private final  Throwable throwable;
    private final HttpStatus httpStatus;

    public errorBody(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
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
