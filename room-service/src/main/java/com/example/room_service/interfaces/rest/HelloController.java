package com.example.room_service.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    //porta: 8085
    @GetMapping("/teste")
    public String name() {
        return "Testando room-service";
    }
}