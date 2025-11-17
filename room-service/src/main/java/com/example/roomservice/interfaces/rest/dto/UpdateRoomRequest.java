package com.example.roomservice.interfaces.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateRoomRequest(
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    String name,

    @Min(value = 1, message = "Capacidade deve ser pelo menos 1")
    Integer capacity
) {}
