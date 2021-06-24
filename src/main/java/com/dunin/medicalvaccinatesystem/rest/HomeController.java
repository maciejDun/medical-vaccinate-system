package com.dunin.medicalvaccinatesystem.rest;

import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(HomeController.BASE_URL)
public class HomeController {
    static final String BASE_URL = "/";

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;

    @GetMapping
    public Map<String, String> getUserName() {
        return Collections.singletonMap("name", oAuth2AttributeExtractor.getEmail());
    }
}
