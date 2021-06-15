package com.dunin.medicalvaccinatesystem.security.securityConfig;

import com.dunin.medicalvaccinatesystem.security.oauth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/oauth2/**", "/login**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN") // disable for Angular testing
            .antMatchers("/user/**").hasAnyRole("ADMIN", "USER") // disable for Angular testing
//            .antMatchers("/admin/**").permitAll() //permit for Angular testing
//            .antMatchers("/user/**").permitAll() //permit for Angular testing
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
            .and()
            .successHandler(this::onAuthenticationSuccess);
    }

    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Authentication authentication) throws IOException {

        customOAuth2UserService.processOAuthPostLogin();

        httpServletResponse.sendRedirect("/user");
    }
}
