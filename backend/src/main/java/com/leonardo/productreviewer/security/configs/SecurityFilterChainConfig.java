package com.leonardo.productreviewer.security.configs;

import com.leonardo.productreviewer.security.filters.UsernameAndPasswordAuthenticationFilter;
import com.leonardo.productreviewer.security.jwt.JwtGenerator;
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

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        UsernameAndPasswordAuthenticationFilter authenticationFilter =
                new UsernameAndPasswordAuthenticationFilter(authenticationProvider, jwtGenerator);
        authenticationFilter.setFilterProcessesUrl("/login");
        authenticationFilter.setPostOnly(true);

        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable());

        http.addFilter(authenticationFilter);
        //TODO: Implement authorization filter

        return http.build();
    }
}
