package com.leonardo.productreviewer.security.jwt;

import com.leonardo.productreviewer.security.users.ProductReviewerUserDetails;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

@AllArgsConstructor
@Component
public class JwtGenerator {

    private final SecretKey secretKey;
    private final JwtConfig jwtConstants;


    public String generate(Authentication authentication) {

        ProductReviewerUserDetails userDetails = (ProductReviewerUserDetails) authentication.getPrincipal();

        String jwt = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(JwtConfig.AUTHORITIES, userDetails.getAuthorities())
                .setIssuedAt(getIssuedAt())
                .setExpiration(getExpiration())
                .signWith(secretKey)
                .compact();

        return jwt;
    }

    private Date getIssuedAt() {
        Instant instant = LocalDateTime
                .now()
                .toInstant(getZoneOffset());
        return Date.from(instant);
    }

    private Date getExpiration() {
        Instant instant = LocalDateTime
                .now()
                .plusDays(jwtConstants.getTokenExpirationAfterDays())
                .toInstant(getZoneOffset());
        return Date.from(instant);
    }

    private ZoneOffset getZoneOffset() {
        Integer timeZoneOffset = TimeZone
                .getDefault()
                .getRawOffset() / 1000 / 60 / 60;

        return ZoneOffset.ofHours(timeZoneOffset);
    }

}
