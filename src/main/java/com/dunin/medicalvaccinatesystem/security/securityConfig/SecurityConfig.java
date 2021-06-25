package com.dunin.medicalvaccinatesystem.security.securityConfig;

import com.dunin.medicalvaccinatesystem.security.oauth.service.CustomOAuth2UserService;
import com.dunin.medicalvaccinatesystem.security.token.InMemoryRequestRepository;
import com.dunin.medicalvaccinatesystem.security.token.TokenFilter;
import com.dunin.medicalvaccinatesystem.security.token.TokenStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenStore tokenStore;
    private final TokenFilter tokenFilter;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors()
            .and()
            .authorizeRequests()
            .antMatchers("/oauth2/**", "/login**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
            .and()
            .authorizationEndpoint()
            .authorizationRequestRepository(new InMemoryRequestRepository())
            .and()
            .successHandler(this::onAuthenticationSuccess);

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Authentication authentication) throws IOException {

        Authentication changedAuthIfAdmin = customOAuth2UserService.processOAuthPostLogin(authentication);

        String token = tokenStore.generateToken(changedAuthIfAdmin);
        httpServletResponse.getWriter().write(
                objectMapper.writeValueAsString(Collections.singletonMap("accessToken", token)));


    }
}
