package com.dunin.medicalvaccinatesystem.security.oauth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    private SecurityContext context;
    private Authentication authentication;

    public SecurityContext getContext() {
            this.context = SecurityContextHolder.getContext();
        return context;
    }

    public Authentication getAuthentication() {
            this.authentication = getContext().getAuthentication();
        return authentication;
    }
}
