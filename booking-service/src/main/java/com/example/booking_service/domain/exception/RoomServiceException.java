package com.example.booking_service.domain.exception;

public class RoomServiceException extends RuntimeException {
    public RoomServiceException(String message, Throwable cause) {
        super("Error communicating with room-service: " + message, cause);
    }
}
