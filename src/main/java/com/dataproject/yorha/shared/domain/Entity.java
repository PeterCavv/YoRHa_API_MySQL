package com.dataproject.yorha.shared.domain;

import java.util.Objects;

public abstract class Entity<ID> {
    protected final ID id;

    protected Entity(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) return true;
        if(!(object instanceof Entity<?> other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
