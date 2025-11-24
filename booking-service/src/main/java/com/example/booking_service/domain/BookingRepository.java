package com.example.booking_service.domain;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    public Booking save(Booking booking) {
        bookings.put(booking.getId().toString(), booking);
        return booking;
    }

    public Optional<Booking> findById(String id) {
        return Optional.ofNullable(bookings.get(id));
    }

    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }

    public void deleteById(String id) {
        bookings.remove(id);
    }
}