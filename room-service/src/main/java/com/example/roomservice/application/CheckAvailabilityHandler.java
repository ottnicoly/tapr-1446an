package com.example.roomservice.application;

import com.example.roomservice.application.command.CheckAvailabilityQuery;
import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.RoomRepository;
import com.example.roomservice.domain.exception.RoomNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckAvailabilityHandler {

    private final RoomRepository roomRepository;

    public CheckAvailabilityHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public boolean handle(CheckAvailabilityQuery query) {
        Room room = roomRepository.findById(query.roomId())
            .orElseThrow(() -> new RoomNotFoundException(query.roomId()));

        return room.canAccommodate(query.numberOfPeople());
    }
}
