package com.example.booking_service.domain.exception;

public class BookingConflictException extends RuntimeException {
    public BookingConflictException(String roomId) {
        super("Room with id " + roomId + " already has a booking in the requested time slot");
    }
}
