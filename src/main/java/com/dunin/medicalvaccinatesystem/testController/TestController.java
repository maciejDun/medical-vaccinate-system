package com.dunin.medicalvaccinatesystem.testController;

import com.dunin.medicalvaccinatesystem.security.oauthService.JwtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private JwtMapper jwtMapper;


    @GetMapping("/google")
    public String returnTest() {
        return "Hello you successfully logged in using Google account id: ";


    }

    @GetMapping("admin")
    public String returnAdmin() {
        return "hello admin ";
    }

    @GetMapping("/")
    public String returnHome() {
        return "Welcome to the vaccination registration system ";
    }

}
