package com.dataproject.yorha.shared.domain;

import java.time.Instant;

public abstract class DomainEvent {
    private final Instant occurredOn = Instant.now();

    public Instant occurredOn() {
        return occurredOn;
    }
}
