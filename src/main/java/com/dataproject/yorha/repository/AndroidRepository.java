package com.dataproject.yorha.repository;

import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AndroidRepository extends JpaRepository<Android, Long> {
    List<Android> findByStatus(Status status);
