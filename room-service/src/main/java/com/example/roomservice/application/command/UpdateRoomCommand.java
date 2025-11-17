package com.example.roomservice.application.command;

import com.example.roomservice.domain.vo.RoomId;

public record UpdateRoomCommand(
    RoomId roomId,
    String name,
    Integer capacity
) {}
