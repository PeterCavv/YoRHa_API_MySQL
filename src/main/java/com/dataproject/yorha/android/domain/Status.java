package com.dataproject.yorha.android.domain;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("Operative"),
    INACTIVE("Out of service");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    boolean isOperative() {
        return this == ACTIVE;
    }

    boolean isInactive() {
        return this == INACTIVE;
    }
}
