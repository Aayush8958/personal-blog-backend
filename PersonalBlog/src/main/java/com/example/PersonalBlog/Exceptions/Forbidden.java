package com.example.PersonalBlog.Exceptions;

public class Forbidden extends RuntimeException {
    public Forbidden(String message) {
        super(message);
    }
}
