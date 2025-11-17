package com.example.roomservice.domain;

import com.example.roomservice.domain.vo.RoomId;
import com.example.roomservice.domain.vo.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Room save(Room room);

    Optional<Room> findById(RoomId id);

    List<Room> findAll();

    List<Room> findAvailableRooms();

    List<Room> findByType(RoomType type);

    void deleteById(RoomId id);

    boolean existsById(RoomId id);

    boolean existsByName(String name);
}
