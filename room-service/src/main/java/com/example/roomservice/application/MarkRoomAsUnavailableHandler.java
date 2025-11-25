package com.example.roomservice.application;

import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.RoomRepository;
import com.example.roomservice.domain.exception.RoomNotFoundException;
import com.example.roomservice.domain.vo.RoomId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MarkRoomAsUnavailableHandler {

    private final RoomRepository roomRepository;

    public MarkRoomAsUnavailableHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void handle(UUID roomId) {
        RoomId id = new RoomId(roomId);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));

        room.markAsUnavailable();
        roomRepository.save(room);
    }
}
