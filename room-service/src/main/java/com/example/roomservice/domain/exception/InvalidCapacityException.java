package com.example.roomservice.domain.exception;

public class InvalidCapacityException extends RuntimeException {

    public InvalidCapacityException(String message) {
        super(message);
    }

    public InvalidCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
