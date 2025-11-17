package com.example.roomservice.domain.vo;

public record Availability(boolean value) {

    public static Availability available() {
        return new Availability(true);
    }

    public static Availability unavailable() {
        return new Availability(false);
    }

    public boolean isAvailable() {
        return value;
    }

    public boolean isUnavailable() {
        return !value;
    }
}
