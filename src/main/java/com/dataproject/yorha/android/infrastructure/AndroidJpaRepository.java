package com.dataproject.yorha.android.infrastructure;

import com.dataproject.yorha.android.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AndroidJpaRepository extends JpaRepository<AndroidEntity, Long> {
    List<AndroidEntity> findAllByStatus(Status status);

    long countByTypeId(Long typeId);
}
