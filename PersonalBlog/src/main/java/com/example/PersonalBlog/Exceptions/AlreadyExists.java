package com.example.PersonalBlog.Exceptions;

public class AlreadyExists extends RuntimeException {
    public AlreadyExists(String message) {
        super(message);
    }

  public AlreadyExists(String message, Throwable cause) {
    super(message, cause);
  }

  public AlreadyExists(Throwable cause) {
    super(cause);
  }


}
