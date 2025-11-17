package com.example.roomservice.domain.vo;

import com.example.roomservice.domain.exception.InvalidCapacityException;

public record Capacity(int value) {

    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = 1000;

    public Capacity {
        if (value < MIN_CAPACITY) {
            throw new InvalidCapacityException(
                "Capacidade deve ser pelo menos " + MIN_CAPACITY + " pessoa(s)"
            );
        }

        if (value > MAX_CAPACITY) {
            throw new InvalidCapacityException(
                "Capacidade máxima é de " + MAX_CAPACITY + " pessoas"
            );
        }
    }

    public Capacity add(int additional) {
        if (additional <= 0) {
            throw new InvalidCapacityException("Valor a adicionar deve ser positivo");
        }
        return new Capacity(value + additional);
    }

    public Capacity subtract(int reduction) {
        if (reduction <= 0) {
            throw new InvalidCapacityException("Valor a subtrair deve ser positivo");
        }
        return new Capacity(value - reduction);
    }

    public boolean canAccommodate(int numberOfPeople) {
        return value >= numberOfPeople;
    }
}
