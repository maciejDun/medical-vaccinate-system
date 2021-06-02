package com.dunin.medicalvaccinatesystem.oauthService;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
