package com.example.account_app.service;

import com.example.account_app.model.User;
import com.example.account_app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Преобразуем User в UserDetails (нужно будет реализовать UserDetails или создать адаптер)
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                /* тут роли/authorities, например Collections.emptyList() */
                java.util.Collections.emptyList()
        );
    }
}
