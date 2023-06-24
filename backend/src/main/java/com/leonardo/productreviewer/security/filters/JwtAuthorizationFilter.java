package com.leonardo.productreviewer.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.productreviewer.security.dtos.StandardErrorResponseDTO;
import com.leonardo.productreviewer.security.jwt.JwtConfig;
import com.leonardo.productreviewer.security.jwt.JwtValidator;
import graphql.com.google.common.base.Strings;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtValidator jwtValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(JwtConfig.AUTHORIZATION_HEADER);

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authorizationHeader.replace("Bearer ", "");

        try {
            Authentication authentication = jwtValidator.validate(jwt);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            this.addResponseBody(response,
                    StandardErrorResponseDTO.builder()
                            .message("Acesso não authorizado.")
                            .status(HttpStatus.FORBIDDEN.value())
                            .timestamp(System.currentTimeMillis())
                            .path(request.getServletPath())
                            .build()
            );
            response.setStatus(HttpStatus.FORBIDDEN.value());

            //TODO: Realizar essa resposta utilizando o formato do graphql
        }


    }

    private void addResponseBody(HttpServletResponse response, Object object) throws IOException {
        response.getWriter().print(
                new ObjectMapper().writeValueAsString(
                        object
                )
        );
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8");
    }

}
