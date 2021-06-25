package com.dunin.medicalvaccinatesystem.security.oauth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    public Authentication getAuthentication() {
        return getContext().getAuthentication();
    }
}
