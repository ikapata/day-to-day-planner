package com.ikadev.daybydayplanner.service;
import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import com.ikadev.daybydayplanner.persistence.repository.DayEntryRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
}
