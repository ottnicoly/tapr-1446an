package com.example.roomservice.domain;

import com.example.roomservice.domain.exception.RoomNotAvailableException;
import com.example.roomservice.domain.vo.*;

public class Room {

    private final RoomId id;
    private RoomName name;
    private Capacity capacity;
    private final RoomType type;
    private Availability availability;

    private Room(RoomId id, RoomName name, Capacity capacity, RoomType type, Availability availability) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.availability = availability;
    }

    public static Room create(RoomName name, Capacity capacity, RoomType type) {
        return new Room(
                RoomId.generate(),
                name,
                capacity,
                type,
                Availability.available());
    }

    public static Room reconstruct(RoomId id, RoomName name, Capacity capacity, RoomType type,
            Availability availability) {
        return new Room(id, name, capacity, type, availability);
    }

    public void markAsUnavailable() {
        if (availability.isUnavailable()) {
            throw new RoomNotAvailableException("Sala já está indisponível");
        }
        this.availability = Availability.unavailable();
    }

    public void markAsAvailable() {
        if (availability.isAvailable()) {
            return;
        }
        this.availability = Availability.available();
    }

    public void updateName(RoomName newName) {
        this.name = newName;
    }

    public void increaseCapacity(int additionalSeats) {
        this.capacity = capacity.add(additionalSeats);
    }

    public void decreaseCapacity(int reduction) {
        this.capacity = capacity.subtract(reduction);
    }

    public void updateCapacity(Capacity newCapacity) {
        this.capacity = newCapacity;
    }

    public boolean canAccommodate(int numberOfPeople) {
        if (availability.isUnavailable()) {
            return false;
        }
        return capacity.canAccommodate(numberOfPeople);
    }

    public boolean isAvailable() {
        return availability.isAvailable();
    }

    public RoomId getId() {
        return id;
    }

    public RoomName getName() {
        return name;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public RoomType getType() {
        return type;
    }

    public Availability getAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name=" + name +
                ", capacity=" + capacity +
                ", type=" + type +
                ", availability=" + availability +
                '}';
    }
}
