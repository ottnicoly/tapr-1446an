package com.example.booking_service.domain.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String id) {
        super("Booking not found with id: " + id);
    }
}
