package com.dataproject.yorha.android.domain;

import java.util.List;
import java.util.Optional;

public interface AndroidRepository {
    Android save(Android android);

    Optional<Android> findById(AndroidId id);

    List<Android> findAll();

    List<Android> findAllByActive();

    long countByTypeId(TypeId typeId);
}
