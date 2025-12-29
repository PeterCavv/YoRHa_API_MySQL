package com.dataproject.yorha.model;

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
