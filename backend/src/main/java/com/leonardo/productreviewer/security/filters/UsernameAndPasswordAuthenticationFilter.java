package com.leonardo.productreviewer.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.productreviewer.security.dtos.CredentialsRequestDTO;
import com.leonardo.productreviewer.security.dtos.JWTResponseDTO;
import com.leonardo.productreviewer.security.jwt.JwtGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@AllArgsConstructor
public class UsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtGenerator jwtGenerator;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            CredentialsRequestDTO credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialsRequestDTO.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    credentials.username(),
                    credentials.password()
            );

            return this.authenticationProvider.authenticate(authentication);

        } catch (IOException e) {
            //Todo: StandardError for this response
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String jwt = jwtGenerator.generate(authResult);

        final String type = "Bearer";

        response.getOutputStream()
                .print(
                        new ObjectMapper().writeValueAsString(
                                new JWTResponseDTO(type, type + " " + jwt)
                        )
                );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}
