package com.ikadev.daybydayplanner.persistence.repository;

import com.ikadev.daybydayplanner.persistence.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void after() {
        userRepository.deleteAll();
    }


    @Test
    void findByUsername() {
        String username = "test";
        User user = User.builder().username(username).build();
        String username1 = "test2";
        User user1 = User.builder().username(username1).build();
        userRepository.saveAll(List.of(user, user1));
        Optional<User> result = userRepository.findByUsername(username);

        Optional<User> expected = Optional.of(user);

        assertEquals(expected, result);
    }

    @Test
    void findByUsernameIfUserNotExists() {
        String username = "test";
        Optional<User> result = userRepository.findByUsername(username);

        Optional<User> expected = Optional.empty();

        assertEquals(expected, result);
    }

    @Test
    void existsByUsernameIfExists() {
        String username = "test";
        User user = User.builder().username(username).build();
        userRepository.save(user);
        boolean result = userRepository.existsByUsername(username);

        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    void existsByUsernameIfNotExists() {
        String username = "test";
        boolean result = userRepository.existsByUsername(username);

        boolean expected = false;

        assertEquals(expected, result);
    }
}