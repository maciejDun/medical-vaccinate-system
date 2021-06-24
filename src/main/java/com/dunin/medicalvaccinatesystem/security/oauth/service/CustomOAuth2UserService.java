package com.dunin.medicalvaccinatesystem.security.oauth.service;

import com.dunin.medicalvaccinatesystem.buissnessService.UserService;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import com.dunin.medicalvaccinatesystem.security.oauth.model.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;
    private final SecurityContextService securityContextService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return new CustomOAuth2User(oAuth2User);
    }

    public Authentication processOAuthPostLogin(Authentication authentication) {
        UserEntity user = userService.createIfNotExist(userService.getUserEntityOptional());
        if (userService.isAdmin(user)) {
            return grantAdminAuthorities(authentication);
        }
        return securityContextService.getAuthentication();
    }

    private Authentication grantAdminAuthorities(Authentication authentication) {
        List<GrantedAuthority> updatedAuthorities =
                new ArrayList<>(authentication.getAuthorities());

        updatedAuthorities.add(new SimpleGrantedAuthority(Roles.ROLE_ADMIN.toString()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                authentication.getCredentials(), updatedAuthorities);

        securityContextService.getContext().setAuthentication(newAuth);
        return newAuth;
    }
}
