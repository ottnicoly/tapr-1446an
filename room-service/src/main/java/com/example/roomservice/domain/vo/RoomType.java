package com.example.roomservice.domain.vo;

public enum RoomType {
    MEETING_ROOM("Sala de Reunião"),
    CLASSROOM("Sala de Aula"),
    AUDITORIUM("Auditório"),
    LABORATORY("Laboratório"),
    CONFERENCE_ROOM("Sala de Conferência"),
    TRAINING_ROOM("Sala de Treinamento");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
