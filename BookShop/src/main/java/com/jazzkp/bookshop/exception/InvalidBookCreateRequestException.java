package com.jazzkp.bookshop.exception;

public class InvalidBookCreateRequestException extends RuntimeException{
    public InvalidBookCreateRequestException() {
    }

    public InvalidBookCreateRequestException(String message) {
        super(message);
    }
}
