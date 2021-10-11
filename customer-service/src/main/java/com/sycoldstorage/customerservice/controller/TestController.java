package com.sycoldstorage.customerservice.controller;

import com.sycoldstorage.customerservice.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/applicationName")
    public String getApplicationName() {
        return configService.getApplicationName();
    }
}
