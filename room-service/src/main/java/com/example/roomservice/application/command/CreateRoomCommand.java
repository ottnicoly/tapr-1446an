package com.example.roomservice.application.command;

import com.example.roomservice.domain.vo.RoomType;

public record CreateRoomCommand(
    String name,
    int capacity,
    RoomType type
) {}
