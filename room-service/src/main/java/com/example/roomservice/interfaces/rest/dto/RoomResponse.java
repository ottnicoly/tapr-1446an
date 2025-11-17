package com.example.roomservice.interfaces.rest.dto;

import com.example.roomservice.domain.Room;

import java.util.UUID;

public record RoomResponse(
    UUID id,
    String name,
    int capacity,
    String type,
    boolean available
) {

    public static RoomResponse from(Room room) {
        return new RoomResponse(
            room.getId().value(),
            room.getName().value(),
            room.getCapacity().value(),
            room.getType().getDisplayName(),
            room.getAvailability().isAvailable()
        );
    }
}
