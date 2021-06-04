package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdminController.BASE_URL)
@RequiredArgsConstructor
public class AdminController {

    static final String BASE_URL = "/admin";

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;

    @GetMapping("")
    public String returnAdmin() {
        return "hello admin "
                + oAuth2AttributeExtractor.getEmail();
    }
}
