package com.dataproject.yorha.android.domain;

import com.dataproject.yorha.shared.domain.ValueObject;

public record TypeId(Long value) implements ValueObject {
    public TypeId {
        if (value == null) {
            throw new IllegalArgumentException("TypeId can't be null");
        }
    }
}
