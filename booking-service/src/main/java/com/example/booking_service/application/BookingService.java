package com.example.booking_service.application;

import com.example.booking_service.domain.Booking;
import com.example.booking_service.domain.BookingRepository;
import com.example.booking_service.domain.exception.BookingConflictException;
import com.example.booking_service.domain.exception.BookingNotFoundException;
import com.example.booking_service.domain.exception.RoomNotAvailableException;
import com.example.booking_service.domain.exception.RoomServiceException;
import com.example.booking_service.domain.vo.BookingId;
import com.example.booking_service.interfaces.rest.dto.AvailabilityResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RestTemplate restTemplate;

    @Value("${room-service.url:http://room-service:8080}")
    private String roomServiceUrl;

    public BookingService(BookingRepository bookingRepository, RestTemplate restTemplate) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
    }

    public Booking createBooking(UUID userId, String roomId, LocalDateTime startTime, LocalDateTime endTime,
                                   String roomType, int numberOfPeople) {

        validateDates(startTime, endTime);
        checkTimeConflict(roomId, startTime, endTime);
        checkRoomAvailability(roomId, numberOfPeople);

        Booking booking = new Booking(
            BookingId.generate(),
            userId,
            roomId,
            startTime,
            endTime,
            roomType,
            numberOfPeople
        );

        Booking savedBooking = bookingRepository.save(booking);

        markRoomAsUnavailable(roomId);

        return savedBooking;
    }

    public List<Booking> listAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    public void cancelBooking(String id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        String roomId = booking.getRoomId();

        bookingRepository.deleteById(id);

        markRoomAsAvailable(roomId);
    }

    private void validateDates(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start time and end time cannot be null");
        }

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot create booking in the past");
        }
    }

    private void checkTimeConflict(String roomId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Booking> existingBookings = bookingRepository.findByRoomId(roomId);

        boolean hasConflict = existingBookings.stream()
                .anyMatch(booking -> isTimeOverlapping(
                        booking.getStartTime(),
                        booking.getEndTime(),
                        startTime,
                        endTime
                ));

        if (hasConflict) {
            throw new BookingConflictException(roomId);
        }
    }

    private boolean isTimeOverlapping(LocalDateTime start1, LocalDateTime end1,
                                       LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    private void checkRoomAvailability(String roomId, int numberOfPeople) {
        try {
            String url = String.format("%s/rooms/%s/availability?numberOfPeople=%d",
                    roomServiceUrl, roomId, numberOfPeople);

            AvailabilityResponse response = restTemplate.getForObject(url, AvailabilityResponse.class);

            if (response == null || !response.isAvailable()) {
                throw new RoomNotAvailableException(roomId);
            }
        } catch (Exception e) {
            if (e instanceof RoomNotAvailableException) {
                throw e;
            }
            throw new RoomServiceException("Failed to check room availability", e);
        }
    }

    private void markRoomAsUnavailable(String roomId) {
        try {
            String url = String.format("%s/rooms/%s/mark-unavailable", roomServiceUrl, roomId);
            restTemplate.patchForObject(url, null, Void.class);
        } catch (Exception e) {
            System.err.println("Failed to mark room as unavailable: " + e.getMessage());
        }
    }

    private void markRoomAsAvailable(String roomId) {
        try {
            String url = String.format("%s/rooms/%s/mark-available", roomServiceUrl, roomId);
            restTemplate.patchForObject(url, null, Void.class);
        } catch (Exception e) {
            System.err.println("Failed to mark room as available: " + e.getMessage());
        }
    }
}
