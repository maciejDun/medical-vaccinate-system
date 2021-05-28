package com.dunin.medicalvaccinatesystem.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String returnTest() {
        return "Hello you successfully logged in using Google account";
    }
}
