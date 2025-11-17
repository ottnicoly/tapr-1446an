package com.example.roomservice.infrastructure.persistence;

import com.example.roomservice.domain.vo.RoomType;
import com.example.roomservice.infrastructure.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataRoomJpa extends JpaRepository<RoomEntity, UUID> {

    List<RoomEntity> findByAvailableTrue();

    List<RoomEntity> findByType(RoomType type);

    boolean existsByName(String name);
}
