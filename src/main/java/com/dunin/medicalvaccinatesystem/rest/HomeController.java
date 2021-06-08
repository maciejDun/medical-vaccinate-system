package com.dunin.medicalvaccinatesystem.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HomeController.BASE_URL)
public class HomeController {
    static final String BASE_URL = "/";

    @GetMapping("/")
    public String returnHome() {
        return "Welcome to the vaccination registration system "
                + SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
