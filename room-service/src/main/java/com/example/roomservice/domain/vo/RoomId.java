package com.example.roomservice.domain.vo;

import java.util.UUID;

public record RoomId(UUID value) {

    public RoomId {
        if (value == null) {
            throw new IllegalArgumentException("RoomId não pode ser nulo");
        }
    }

    public static RoomId generate() {
        return new RoomId(UUID.randomUUID());
    }

    public static RoomId from(String id) {
        try {
            return new RoomId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID inválido: " + id, e);
        }
    }
}
