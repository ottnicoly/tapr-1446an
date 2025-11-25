package com.example.booking_service.interfaces.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.booking_service.domain.Booking;
import com.example.booking_service.domain.BookingRepository;
import com.example.booking_service.interfaces.rest.dto.AvailabilityResponse;
import com.example.booking_service.interfaces.rest.dto.CreateBookingRequest;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final RestTemplate restTemplate;

    private CreateBookingRequest createBookingRequest;

    private final BookingRepository bookingRepository;

    // porta: 8081
    @GetMapping("/teste")
    public String name() {
        return "Testando booking-service";
    }

    public BookingController(RestTemplate restTemplate, BookingRepository bookingRepository) {
        this.restTemplate = restTemplate;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateBookingRequest request) {
        // Chama o room-service para verificar disponibilidade
        String roomServiceUrl = "http://localhost:8082/rooms/availability";
        AvailabilityResponse availabilityResponse = restTemplate.postForObject(
                roomServiceUrl,
                request,
                AvailabilityResponse.class);

        if (availabilityResponse != null && availabilityResponse.isAvailable()) {
            // Lógica para criar a reserva (booking) aqui
            return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room is not available for the requested dates.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> list() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancel(@PathVariable String id) {
        if (bookingRepository.findById(id).isPresent()) {
            bookingRepository.deleteById(id);
            return ResponseEntity.ok("Booking cancelled successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found.");
        }
    }
}