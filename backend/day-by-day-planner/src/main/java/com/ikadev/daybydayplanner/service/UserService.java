package com.ikadev.daybydayplanner.service;

import com.ikadev.daybydayplanner.persistence.model.Role;
import com.ikadev.daybydayplanner.persistence.model.User;
import com.ikadev.daybydayplanner.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.USER));
        return userRepository.save(user);
    }


    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
