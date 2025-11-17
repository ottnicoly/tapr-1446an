package com.example.roomservice.domain.exception;

import com.example.roomservice.domain.vo.RoomId;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(RoomId roomId) {
        super("Sala não encontrada com ID: " + roomId.value());
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
