package com.example.roomservice.domain.vo;

public record RoomName(String value) {

    public RoomName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Nome da sala não pode ser vazio");
        }

        if (value.length() < 3) {
            throw new IllegalArgumentException("Nome da sala deve ter pelo menos 3 caracteres");
        }

        if (value.length() > 100) {
            throw new IllegalArgumentException("Nome da sala não pode ter mais de 100 caracteres");
        }

        value = value.trim();
    }
}
