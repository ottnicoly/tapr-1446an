package com.example.booking_service.interfaces.rest.dto;

public class AvailabilityResponse {
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}