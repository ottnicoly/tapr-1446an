package com.example.booking_service.interfaces.rest.dto;

import java.time.LocalDateTime;

public class CreateBookingRequest {
    private String roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomType;
    private int numberOfPeople;

    public String getRoomId() {
        return roomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

}
