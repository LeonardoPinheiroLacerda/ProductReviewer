package com.leonardo.productreviewer.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder(5);
        //TODO: Implement some secure way to sign new users using BCryptPasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }
}
