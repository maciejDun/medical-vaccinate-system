package com.dunin.medicalvaccinatesystem.security.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2AttributeExtractor {

    private Object principal;
    private OAuth2User oAuth2User;
    private final SecurityContextService securityContextService;


    public String getEmail() {
        getPrincipal();
        return oAuth2User.getAttribute("email");
    }

    private void getPrincipal() {
        this.principal = securityContextService.getAuthentication().getPrincipal();
        this.oAuth2User = (OAuth2User) principal;
    }

    public OAuth2User getOAuth2User() {
        return this.oAuth2User;
    }
}
