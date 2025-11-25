package com.example.booking_service.infrastructure.persistence;

import com.example.booking_service.domain.Booking;
import com.example.booking_service.domain.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaBookingRepository implements BookingRepository {

    private final SpringDataBookingJpa jpaRepository;
    private final BookingMapper mapper;

    public JpaBookingRepository(SpringDataBookingJpa jpaRepository, BookingMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Booking save(Booking booking) {
        BookingEntity entity = mapper.toEntity(booking);
        BookingEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Booking> findById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return jpaRepository.findById(uuid)
                    .map(mapper::toDomain);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Booking> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            jpaRepository.deleteById(uuid);
        } catch (IllegalArgumentException e) {
            // ID inválido, não faz nada
        }
    }

    @Override
    public List<Booking> findByRoomId(String roomId) {
        return jpaRepository.findByRoomId(roomId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
