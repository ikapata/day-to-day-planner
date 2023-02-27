package com.ikadev.daytodayplanner.persistence.repository;

import com.ikadev.daytodayplanner.persistence.model.DayEntry;
import com.ikadev.daytodayplanner.persistence.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class DayEntryRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DayEntryRepository dayEntryRepository;

    @AfterEach
    void after() {
        dayEntryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findByUserUsernameAndDate() {
        String username = "test";
        User user = User.builder().username(username).password("test").build();
        userRepository.save(user);
        LocalDate date = LocalDate.now();
        DayEntry dayEntry = DayEntry.builder()
                .user(user)
                .date(date)
                .build();

        LocalDate date2 = LocalDate.of(2020, 3,3);
        DayEntry dayEntry2 = DayEntry.builder()
                .user(user)
                .date(date2)
                .build();
        dayEntryRepository.saveAll(List.of(dayEntry, dayEntry2));

        Optional<DayEntry> result = dayEntryRepository.findByUserUsernameAndDate(username, date);

        Optional<DayEntry> expected = Optional.of(dayEntry);
        assertEquals(expected, result);
    }

    @Test
    void findByUserUsername() {
        String username = "test";
        User user = User.builder().username(username).password("test").build();
        String username1 = "test2";
        User user1 = User.builder().username(username1).build();
        userRepository.saveAll(List.of(user, user1));
        DayEntry dayEntry = DayEntry.builder()
                .user(user)
                .build();

        DayEntry dayEntry2 = DayEntry.builder()
                .user(user1)
                .build();
        dayEntryRepository.saveAll(List.of(dayEntry, dayEntry2));

        List<DayEntry> result = dayEntryRepository.findByUserUsername(username);

        List<DayEntry> expected = List.of(dayEntry);
        assertEquals(expected, result);

    }
}