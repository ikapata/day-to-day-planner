package com.ikadev.daybydayplanner.service;

import com.ikadev.daybydayplanner.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @MockBean
    UserRepository userRepository;
    @MockBean
    PasswordEncoder passwordEncoder;
    

    @Test
    void saveNewUser() {
    }

    @Test
    void usernameExists() {
    }
}