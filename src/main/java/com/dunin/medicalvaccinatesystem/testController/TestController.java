package com.dunin.medicalvaccinatesystem.testController;

import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;

    @GetMapping("/google")
    public String returnTest() {
        return "Hello you successfully logged in using Google account id: "
                + oAuth2AttributeExtractor.getEmail();
    }

    @GetMapping("admin")
    public String returnAdmin() {
        return "hello admin "
                + oAuth2AttributeExtractor.getEmail();
    }

    @GetMapping("/")
    public String returnHome() {
        return "Welcome to the vaccination registration system "
                + SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
