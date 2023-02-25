package com.ikadev.daybydayplanner.service;

import com.ikadev.daybydayplanner.persistence.model.User;
import com.ikadev.daybydayplanner.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @MockBean
    UserRepository userRepository = mock(UserRepository.class);
    @MockBean
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    UserService userService = new UserService(userRepository, passwordEncoder);

    @Test
    void saveNewUser() {
        String username = "test";
        String password = "test";
        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        userService.save(user);

        verify(passwordEncoder).encode(password);
        verify(userRepository).save(user);
    }

    @Test
    void usernameExists() {
        String username = "test";

        userService.usernameExists(username);

        verify(userRepository).existsByUsername(username);
    }

    @Test
    void getUserByUsername() {
        String username = "test";
        User user = User.builder().username(username).build();
        when(userRepository.findByUsername(username)).thenReturn(Optional.ofNullable(user));

        userService.getUserByUsername(username);

        verify(userRepository).findByUsername(username);
    }
}