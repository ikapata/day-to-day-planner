package com.ikadev.daytodayplanner.service;

import com.ikadev.daytodayplanner.persistence.model.DayEntry;
import com.ikadev.daytodayplanner.persistence.model.Mood;
import com.ikadev.daytodayplanner.persistence.model.User;
import com.ikadev.daytodayplanner.persistence.repository.DayEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EntryService {
    private final DayEntryRepository dayEntryRepository;
    private final UserService userService;

    public EntryService(DayEntryRepository dayEntryRepository, UserService userService) {
        this.dayEntryRepository = dayEntryRepository;
        this.userService = userService;
    }

    public List<DayEntry> getAllEntriesForUser(String username) {
        return dayEntryRepository.findByUserUsername(username);
    }

    public Optional<DayEntry> getEntryForUserAndDate(LocalDate date, String username) {
        return dayEntryRepository.findByUserUsernameAndDate(username, date);
    }

    public DayEntry saveEntry(DayEntry dayEntry, String username) {
        User user = userService.getUserByUsername(username);
        dayEntry.setUser(user);
        return dayEntryRepository.save(dayEntry);
    }

    public Map<Mood, List<LocalDate>> getMoodsForYear(int year, String username) {
        LocalDate date = LocalDate.of(year, 1, 1);
        List<DayEntry> entriesForYear = dayEntryRepository.findByUserUsernameAndDateBetween(username, date, date.plusYears(1));
        Map<Mood, List<LocalDate>> result = new HashMap<>();
        Arrays.stream(Mood.values()).forEach(mood -> result.put(mood, new ArrayList<>()));
        entriesForYear.forEach(entry -> result.get(entry.getMood()).add(entry.getDate()));
        return result;
    }
}
