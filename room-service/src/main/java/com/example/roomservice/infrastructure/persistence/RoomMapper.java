package com.example.roomservice.infrastructure.persistence;

import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.vo.*;
import com.example.roomservice.infrastructure.persistence.entity.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomEntity toEntity(Room room) {
        RoomEntity entity = new RoomEntity();
        entity.setId(room.getId().value());
        entity.setName(room.getName().value());
        entity.setCapacity(room.getCapacity().value());
        entity.setType(room.getType());
        entity.setAvailable(room.getAvailability().isAvailable());
        return entity;
    }

    public Room toDomain(RoomEntity entity) {
        return Room.reconstruct(
            new RoomId(entity.getId()),
            new RoomName(entity.getName()),
            new Capacity(entity.getCapacity()),
            entity.getType(),
            new Availability(entity.getAvailable())
        );
    }
}
