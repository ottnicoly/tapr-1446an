package com.example.booking_service.domain.vo;

import java.util.UUID;

public record BookingId(UUID value) {

    public BookingId {
        if (value == null) {
            throw new IllegalArgumentException("BookingId não pode ser nulo");
        }
    }

    public static BookingId generate() {
        return new BookingId(UUID.randomUUID());
    }

    public static BookingId from(String id) {
        try {
            return new BookingId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID inválido: " + id, e);
        }
    }
}
