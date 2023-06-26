package com.leonardo.productreviewer.configs;

import com.leonardo.productreviewer.models.User;
import com.leonardo.productreviewer.models.enums.Role;
import com.leonardo.productreviewer.repositories.UserRepository;
import graphql.com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@AllArgsConstructor

@Configuration
@Profile("dev")
public class DevelopmentUsersConfig implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("dev_common").isEmpty()) {
            User common = User.builder()
                    .username("dev_common")
                    .password("dev_common")
                    .roles(Sets.newHashSet(Role.COMMON))
                    .build();
            userRepository.save(common);
            log.info("dev_common user created.");
        }
        if (userRepository.findByUsername("dev_admin").isEmpty()) {
            User admin = User.builder()
                    .username("dev_admin")
                    .password("dev_admin")
                    .roles(Sets.newHashSet(Role.ADMIN))
                    .build();
            userRepository.save(admin);
            log.info("dev_admin user created.");
        }
    }
}
