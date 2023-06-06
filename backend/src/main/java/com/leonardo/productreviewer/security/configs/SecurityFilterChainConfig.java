package com.leonardo.productreviewer.security.configs;

import com.leonardo.productreviewer.security.filters.JwtAuthorizationFilter;
import com.leonardo.productreviewer.security.filters.UsernameAndPasswordAuthenticationFilter;
import com.leonardo.productreviewer.security.jwt.JwtGenerator;
import com.leonardo.productreviewer.security.jwt.JwtValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityFilterChainConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtGenerator jwtGenerator;
    private final JwtValidator jwtValidator;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        UsernameAndPasswordAuthenticationFilter authenticationFilter =
                new UsernameAndPasswordAuthenticationFilter(authenticationProvider, jwtGenerator);
        authenticationFilter.setFilterProcessesUrl("/login");
        authenticationFilter.setPostOnly(true);

        JwtAuthorizationFilter jwtVerifierFilter = new JwtAuthorizationFilter(jwtValidator);

        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable());

        http
                .addFilter(authenticationFilter)
                .addFilterAfter(jwtVerifierFilter, UsernameAndPasswordAuthenticationFilter.class);

        return http.build();
    }
}
