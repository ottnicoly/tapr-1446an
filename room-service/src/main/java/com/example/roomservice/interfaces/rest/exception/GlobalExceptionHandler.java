package com.example.roomservice.interfaces.rest.exception;

import com.example.roomservice.domain.exception.InvalidCapacityException;
import com.example.roomservice.domain.exception.RoomNotAvailableException;
import com.example.roomservice.domain.exception.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ProblemDetail handleRoomNotFound(RoomNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );
        problemDetail.setTitle("Sala não encontrada");
        problemDetail.setType(URI.create("https://api.roomservice.com/errors/room-not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(InvalidCapacityException.class)
    public ProblemDetail handleInvalidCapacity(InvalidCapacityException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            ex.getMessage()
        );
        problemDetail.setTitle("Capacidade inválida");
        problemDetail.setType(URI.create("https://api.roomservice.com/errors/invalid-capacity"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ProblemDetail handleRoomNotAvailable(RoomNotAvailableException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.CONFLICT,
            ex.getMessage()
        );
        problemDetail.setTitle("Sala indisponível");
        problemDetail.setType(URI.create("https://api.roomservice.com/errors/room-not-available"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            ex.getMessage()
        );
        problemDetail.setTitle("Argumento inválido");
        problemDetail.setType(URI.create("https://api.roomservice.com/errors/invalid-argument"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            "Erro de validação: " + errors
        );
        problemDetail.setTitle("Erro de validação");
        problemDetail.setType(URI.create("https://api.roomservice.com/errors/validation"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro interno do servidor. Por favor, tente novamente mais tarde."
        );
        problemDetail.setTitle("Erro interno");
        problemDetail.setType(URI.create("https://api.roomservice.com/errors/internal"));
        problemDetail.setProperty("timestamp", Instant.now());

        System.err.println("Erro não tratado: " + ex.getMessage());
        ex.printStackTrace();

        return problemDetail;
    }
}
