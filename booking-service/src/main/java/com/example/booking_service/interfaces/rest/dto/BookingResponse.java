package com.example.booking_service.interfaces.rest.dto;

import com.example.booking_service.domain.Booking;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingResponse {
    private String id;
    private UUID userId;
    private String roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomType;
    private int numberOfPeople;

    public BookingResponse(String id, UUID userId, String roomId, LocalDateTime startTime, LocalDateTime endTime,
                            String roomType, int numberOfPeople) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomType = roomType;
        this.numberOfPeople = numberOfPeople;
    }

    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getId().toString(),
                booking.getUserId(),
                booking.getRoomId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getRoomType(),
                booking.getNumberOfPeople()
        );
    }

    public String getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

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
