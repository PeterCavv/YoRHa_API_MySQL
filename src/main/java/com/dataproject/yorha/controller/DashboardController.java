package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.DashboardDTO;
import com.dataproject.yorha.service.DashboardService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardDTO> handle() {
        DashboardDTO dto = dashboardService.handle();

        return ResponseEntity.ok(dto);
    }
}
