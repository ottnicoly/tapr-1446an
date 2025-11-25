package com.example.booking_service.domain.exception;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException(String roomId) {
        super("Room with id " + roomId + " is not available for the requested dates or capacity");
    }
}
