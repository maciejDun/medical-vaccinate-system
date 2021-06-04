package com.dunin.medicalvaccinatesystem.security.securityConfig;

import com.dunin.medicalvaccinatesystem.security.oauth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/admin**").hasRole("ADMIN")
            .antMatchers("/google/**").hasAnyRole("ADMIN", "USER")
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
            .and()
            .successHandler((httpServletRequest, httpServletResponse, authentication) -> {

                customOAuth2UserService.processOAuthPostLogin();

                httpServletResponse.sendRedirect("/google");
            });
    }

}
