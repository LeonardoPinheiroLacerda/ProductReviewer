package com.leonardo.productreviewer.security.users;

import com.leonardo.productreviewer.models.User;
import com.leonardo.productreviewer.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record ProductReviewerUserDetailsService(
        UserRepository userRepository
) implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Esse nome de usuário não pode ser encontrado."));

        return new ProductReviewerUserDetails(user);
    }
}
