package com.example.roomservice.application;

import com.example.roomservice.application.command.UpdateRoomCommand;
import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.RoomRepository;
import com.example.roomservice.domain.exception.RoomNotFoundException;
import com.example.roomservice.domain.vo.Capacity;
import com.example.roomservice.domain.vo.RoomName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateRoomHandler {

    private final RoomRepository roomRepository;

    public UpdateRoomHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Room handle(UpdateRoomCommand command) {
        Room room = roomRepository.findById(command.roomId())
            .orElseThrow(() -> new RoomNotFoundException(command.roomId()));

        if (command.name() != null && !command.name().isBlank()) {
            room.updateName(new RoomName(command.name()));
        }

        if (command.capacity() != null) {
            room.updateCapacity(new Capacity(command.capacity()));
        }

        return roomRepository.save(room);
    }
}
