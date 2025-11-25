package com.example.booking_service.interfaces.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking_service.application.BookingService;
import com.example.booking_service.domain.Booking;
import com.example.booking_service.interfaces.rest.dto.BookingResponse;
import com.example.booking_service.interfaces.rest.dto.CreateBookingRequest;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // porta: 8081
    @GetMapping("/teste")
    public String teste() {
        return "Testando booking-service";
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(@RequestBody CreateBookingRequest request) {
        // Gera um userId aleatório (sem autenticação)
        UUID userId = UUID.randomUUID();

        Booking booking = bookingService.createBooking(
                userId,
                request.getRoomId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getRoomType(),
                request.getNumberOfPeople()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BookingResponse.from(booking));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> list() {
        List<BookingResponse> bookings = bookingService.listAllBookings()
                .stream()
                .map(BookingResponse::from)
                .toList();

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable String id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(BookingResponse.from(booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable String id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}