package com.codecool.cookpad.model.entity;

public enum Role {
    USER,
    ADMIN;

    public String name() {
        return "ROLE_" + toString();
    }
}
