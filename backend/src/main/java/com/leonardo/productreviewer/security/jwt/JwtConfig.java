package com.leonardo.productreviewer.security.jwt;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter
@Setter

@Configuration
@ConfigurationProperties("application.jwt")
public class JwtConfig {

    private String secretKey;
    private Long tokenExpirationAfterDays;

    public static final String AUTHORITIES = "roles";
    public static final String AUTHORIZATION_HEADER = "Authorization";

}
