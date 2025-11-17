package com.example.roomservice.application;

import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.RoomRepository;
import com.example.roomservice.domain.exception.RoomNotFoundException;
import com.example.roomservice.domain.vo.RoomId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetRoomByIdHandler {

    private final RoomRepository roomRepository;

    public GetRoomByIdHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public Room handle(RoomId roomId) {
        return roomRepository.findById(roomId)
            .orElseThrow(() -> new RoomNotFoundException(roomId));
    }
}
