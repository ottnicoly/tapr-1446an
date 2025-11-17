package com.example.roomservice.interfaces.rest.dto;

import com.example.roomservice.domain.vo.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRoomRequest(
    @NotBlank(message = "Nome da sala é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    String name,

    @NotNull(message = "Capacidade é obrigatória")
    @Min(value = 1, message = "Capacidade deve ser pelo menos 1")
    Integer capacity,

    @NotNull(message = "Tipo da sala é obrigatório")
    RoomType type
) {}
