package com.test.exception;

public class NotVerifiedUserException extends Exception{

    public NotVerifiedUserException() {
        super();
    }

    public NotVerifiedUserException(String message) {
        super(message);
    }
}
