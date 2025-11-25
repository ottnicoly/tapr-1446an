package com.example.roomservice.interfaces.rest;

import com.example.roomservice.application.*;
import com.example.roomservice.application.command.CheckAvailabilityQuery;
import com.example.roomservice.application.command.CreateRoomCommand;
import com.example.roomservice.application.command.UpdateRoomCommand;
import com.example.roomservice.domain.Room;
import com.example.roomservice.domain.vo.RoomId;
import com.example.roomservice.interfaces.rest.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final CreateRoomHandler createRoomHandler;
    private final ListRoomsHandler listRoomsHandler;
    private final GetRoomByIdHandler getRoomByIdHandler;
    private final UpdateRoomHandler updateRoomHandler;
    private final CheckAvailabilityHandler checkAvailabilityHandler;
    private final MarkRoomAsUnavailableHandler markRoomAsUnavailableHandler;
    private final MarkRoomAsAvailableHandler markRoomAsAvailableHandler;

    public RoomController(
        CreateRoomHandler createRoomHandler,
        ListRoomsHandler listRoomsHandler,
        GetRoomByIdHandler getRoomByIdHandler,
        UpdateRoomHandler updateRoomHandler,
        CheckAvailabilityHandler checkAvailabilityHandler,
        MarkRoomAsUnavailableHandler markRoomAsUnavailableHandler,
        MarkRoomAsAvailableHandler markRoomAsAvailableHandler
    ) {
        this.createRoomHandler = createRoomHandler;
        this.listRoomsHandler = listRoomsHandler;
        this.getRoomByIdHandler = getRoomByIdHandler;
        this.updateRoomHandler = updateRoomHandler;
        this.checkAvailabilityHandler = checkAvailabilityHandler;
        this.markRoomAsUnavailableHandler = markRoomAsUnavailableHandler;
        this.markRoomAsAvailableHandler = markRoomAsAvailableHandler;
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody @Valid CreateRoomRequest request) {
        CreateRoomCommand command = new CreateRoomCommand(
            request.name(),
            request.capacity(),
            request.type()
        );

        Room room = createRoomHandler.handle(command);
        RoomResponse response = RoomResponse.from(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> list(
        @RequestParam(required = false, defaultValue = "false") boolean availableOnly
    ) {
        List<Room> rooms = availableOnly
            ? listRoomsHandler.handleAvailableRooms()
            : listRoomsHandler.handle();

        List<RoomResponse> response = rooms.stream()
            .map(RoomResponse::from)
            .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getById(@PathVariable UUID id) {
        Room room = getRoomByIdHandler.handle(new RoomId(id));
        return ResponseEntity.ok(RoomResponse.from(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(
        @PathVariable UUID id,
        @RequestBody @Valid UpdateRoomRequest request
    ) {
        UpdateRoomCommand command = new UpdateRoomCommand(
            new RoomId(id),
            request.name(),
            request.capacity()
        );

        Room room = updateRoomHandler.handle(command);
        return ResponseEntity.ok(RoomResponse.from(room));
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<AvailabilityResponse> checkAvailability(
        @PathVariable UUID id,
        @RequestParam int numberOfPeople
    ) {
        CheckAvailabilityQuery query = new CheckAvailabilityQuery(
            new RoomId(id),
            numberOfPeople
        );

        boolean available = checkAvailabilityHandler.handle(query);
        return ResponseEntity.ok(new AvailabilityResponse(available));
    }

    @PatchMapping("/{id}/mark-unavailable")
    public ResponseEntity<Void> markAsUnavailable(@PathVariable UUID id) {
        markRoomAsUnavailableHandler.handle(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/mark-available")
    public ResponseEntity<Void> markAsAvailable(@PathVariable UUID id) {
        markRoomAsAvailableHandler.handle(id);
        return ResponseEntity.noContent().build();
    }
}
