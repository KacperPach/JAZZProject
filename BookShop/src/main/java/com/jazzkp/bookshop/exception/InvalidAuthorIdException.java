package com.jazzkp.bookshop.exception;

public class InvalidAuthorIdException extends RuntimeException{
    public InvalidAuthorIdException() {
    }

    public InvalidAuthorIdException(String message) {
        super(message);
    }
}
