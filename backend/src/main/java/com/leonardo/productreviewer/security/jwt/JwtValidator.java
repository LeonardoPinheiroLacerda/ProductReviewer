package com.leonardo.productreviewer.security.jwt;

import com.leonardo.productreviewer.security.users.ProductReviewerUserDetails;
import com.leonardo.productreviewer.security.users.ProductReviewerUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@AllArgsConstructor

@Component
public class JwtValidator {

    private final SecretKey secretKey;
    private final ProductReviewerUserDetailsService userDetailsService;

    private Jws<Claims> validateAndGetClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt);
    }

    public Authentication validate(String jwt) {

        Claims body = validateAndGetClaims(jwt).getBody();

        UserDetails userDetails = userDetailsService.loadUserByUsername(body.getSubject());

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

    }

}
