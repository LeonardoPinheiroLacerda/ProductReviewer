package com.leonardo.productreviewer.security.configs;

import com.leonardo.productreviewer.security.jwt.JwtConfig;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@AllArgsConstructor

@Configuration
public class SecretKeyConfig {

    private final JwtConfig jwtConstants;

    @Bean
    SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtConstants.getSecretKey().getBytes());
    }

}
