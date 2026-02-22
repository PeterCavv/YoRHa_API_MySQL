package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.DashboardDTO;
import com.dataproject.yorha.model.Status;
import com.dataproject.yorha.repository.AndroidRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DashboardService {
    private final AndroidRepository androidRepository;

    public DashboardService(AndroidRepository androidRepository) {
        this.androidRepository = androidRepository;
    }

    public DashboardDTO handle() {
        return new DashboardDTO(
                androidRepository.findByStatus(Status.ACTIVE).size(),
                androidRepository.findByStatus(Status.INACTIVE).size()
        );
    }
}
