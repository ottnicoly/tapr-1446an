package com.example.gateway_service.domain.user.vo;


public enum RoleType {
    CUSTOMER(1),
    RECEPTIONIST(2),
    MANAGER(3),
    ADMIN(4);

    private final int level;

    RoleType(int level) {
        this.level = level;
    }

    public boolean covers(RoleType other) {
        return this.level >= other.level;
    }

    public int getLevel() {
        return this.level;
    }
}