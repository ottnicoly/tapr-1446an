package com.example.roomservice.application;

import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListRoomsHandler {

    private final RoomRepository roomRepository;

    public ListRoomsHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public List<Room> handle() {
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Room> handleAvailableRooms() {
        return roomRepository.findAvailableRooms();
    }
}
