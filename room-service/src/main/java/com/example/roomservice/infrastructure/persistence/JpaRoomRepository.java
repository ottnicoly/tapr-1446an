package com.example.roomservice.infrastructure.persistence;

import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.RoomRepository;
import com.example.roomservice.domain.vo.RoomId;
import com.example.roomservice.domain.vo.RoomType;
import com.example.roomservice.infrastructure.persistence.entity.RoomEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaRoomRepository implements RoomRepository {

    private final SpringDataRoomJpa jpaRepository;
    private final RoomMapper mapper;

    public JpaRoomRepository(SpringDataRoomJpa jpaRepository, RoomMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Room save(Room room) {
        RoomEntity entity = mapper.toEntity(room);
        RoomEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Room> findById(RoomId id) {
        return jpaRepository.findById(id.value())
            .map(mapper::toDomain);
    }

    @Override
    public List<Room> findAll() {
        return jpaRepository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public List<Room> findAvailableRooms() {
        return jpaRepository.findByAvailableTrue()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public List<Room> findByType(RoomType type) {
        return jpaRepository.findByType(type)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public void deleteById(RoomId id) {
        jpaRepository.deleteById(id.value());
    }

    @Override
    public boolean existsById(RoomId id) {
        return jpaRepository.existsById(id.value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}
