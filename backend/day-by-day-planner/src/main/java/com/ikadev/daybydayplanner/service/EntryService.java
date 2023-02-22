package com.ikadev.daybydayplanner.service;
import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import com.ikadev.daybydayplanner.persistence.model.Mood;
import com.ikadev.daybydayplanner.persistence.repository.DayEntryRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Service
public class EntryService {
    private final DayEntryRepository dayEntryRepository;

    public EntryService(DayEntryRepository dayEntryRepository) {
        this.dayEntryRepository = dayEntryRepository;
    }

    public List<DayEntry> getAllEntriesForUser(String username) {
        return dayEntryRepository.findByUserUsername(username);
    }

    public Optional<DayEntry> getEntryForUserAndDate(LocalDate date, String username) {
        return dayEntryRepository.findByUserUsernameAndDate(username, date);
    }

    public DayEntry saveEntry(DayEntry dayEntry) {
        return dayEntryRepository.save(dayEntry);
    }

    public Map<Mood, List<LocalDate>> getMoodsForYear(int year, Principal principal) {
        LocalDate date = LocalDate.of(year, 1, 1);
        List<DayEntry> entriesForYear = dayEntryRepository.findByUserUsernameAndDateBetween(
                principal.getName(),
                date,
                date.plusYears(1));
        Map<Mood, List<LocalDate>> result = new HashMap<>();
        Arrays.stream(Mood.values()).forEach(mood -> result.put(mood, new ArrayList<>()));
        entriesForYear.forEach(entry -> {
            result.get(entry.getMood()).add(entry.getDate());
                }
        );
        return result;
    }
}
