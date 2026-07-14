package com.dataproject.yorha.android.domain;

import com.dataproject.yorha.shared.domain.ValueObject;

public record AndroidId(Long value) implements ValueObject {
    public AndroidId {
        if (value == null) {
            throw new IllegalArgumentException("AndroidId can't be null");
        }
    }
}
