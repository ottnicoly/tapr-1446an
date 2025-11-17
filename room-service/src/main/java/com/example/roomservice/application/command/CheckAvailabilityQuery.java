package com.example.roomservice.application.command;

import com.example.roomservice.domain.vo.RoomId;

public record CheckAvailabilityQuery(
    RoomId roomId,
    int numberOfPeople
) {}
