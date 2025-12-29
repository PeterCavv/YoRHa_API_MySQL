package com.dataproject.yorha.model.android;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("Operative"),
    INACTIVE("Out of service");

    private String name;

    Status(String name) {
        this.name = name;
    }
}
