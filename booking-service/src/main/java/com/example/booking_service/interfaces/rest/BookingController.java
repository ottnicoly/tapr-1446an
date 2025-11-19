package com.example.booking_service.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking_service.interfaces.rest.dto.CreateBookingRequest;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private CreateBookingRequest createBookingRequest;

    // porta: 8081
    @GetMapping("/teste")
    public String name() {
        return "Testando booking-service";
    }

    public BookingController(CreateBookingRequest createBookingRequest) {
        this.createBookingRequest = createBookingRequest;
    }
}