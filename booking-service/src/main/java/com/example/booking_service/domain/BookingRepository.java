package com.example.booking_service.domain;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    Booking save(Booking booking);

    Optional<Booking> findById(String id);

    List<Booking> findAll();

    void deleteById(String id);

    List<Booking> findByRoomId(String roomId);

    List<Booking> findByUserId(java.util.UUID userId);
}
