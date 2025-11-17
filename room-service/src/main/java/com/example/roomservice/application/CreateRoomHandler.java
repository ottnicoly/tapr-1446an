package com.example.roomservice.application;

import com.example.roomservice.application.command.CreateRoomCommand;
import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.RoomRepository;
import com.example.roomservice.domain.vo.Capacity;
import com.example.roomservice.domain.vo.RoomName;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRoomHandler {

    private final RoomRepository roomRepository;

    public CreateRoomHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Room handle(CreateRoomCommand command) {
        if (roomRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Já existe uma sala com o nome: " + command.name());
        }

        Room room = Room.create(
            new RoomName(command.name()),
            new Capacity(command.capacity()),
            command.type()
        );

        return roomRepository.save(room);
    }
}
