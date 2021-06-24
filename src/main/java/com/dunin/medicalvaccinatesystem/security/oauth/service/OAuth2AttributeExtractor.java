package com.dunin.medicalvaccinatesystem.security.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2AttributeExtractor {

    private final SecurityContextService securityContextService;


    public String getEmail() {
        OAuth2User user = getPrincipal();
        return user.getAttribute("email");
    }

    private OAuth2User getPrincipal() {
        Object principal = securityContextService.getAuthentication().getPrincipal();
        return (OAuth2User) principal;
    }
}
