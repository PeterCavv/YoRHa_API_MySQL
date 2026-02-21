package com.dataproject.yorha.controller;

import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.service.AndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/androids", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AndroidController {

    private final AndroidService androidService;

    public AndroidController(AndroidService androidService){
        this.androidService = androidService;
    }

    @GetMapping("/androids")
    public List<Android>


}
