package com.rental.security;

import com.rental.Entities.User;
import com.rental.Repositories.UserRepository;
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
        // Παράδειγμα ID που μπορεί να περαστεί
        Long userId = 1L;

        // Χρησιμοποιεί το `findByNameAndId`
        User user = userRepository.findByNameAndId(username, userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with name: " + username + " and id: " + userId);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
