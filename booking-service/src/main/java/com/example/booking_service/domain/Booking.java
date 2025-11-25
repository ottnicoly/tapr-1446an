package com.example.booking_service.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.booking_service.domain.vo.BookingId;

public class Booking {

    private final BookingId id;
    private final UUID userId;
    private final String roomId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String roomType;
    private final int numberOfPeople;

    public Booking(BookingId id, UUID userId, String roomId, LocalDateTime startTime, LocalDateTime endTime, String roomType,
            int numberOfPeople) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomType = roomType;
        this.numberOfPeople = numberOfPeople;
    }

    public BookingId getId() {
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
