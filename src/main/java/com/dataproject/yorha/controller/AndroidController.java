package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.Android.GetAllAndroidDTO;
import com.dataproject.yorha.DTO.Android.GetOneAndroidDTO;
import com.dataproject.yorha.service.AndroidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/androids", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AndroidController {

    private final AndroidService androidService;

    public AndroidController(AndroidService androidService) {
        this.androidService = androidService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllAndroidDTO>> findAll(
            @RequestParam(defaultValue = "false") boolean includeDelete
    ) {
        List<GetAllAndroidDTO> dto = includeDelete ?
                androidService.findAll() :
                androidService.findAllWithoutTrashed();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOneAndroidDTO> findById(@PathVariable Long id) {
        GetOneAndroidDTO dto = androidService.findById(id).isPresent();
        return ResponseEntity.ok(dto);
    }
}
