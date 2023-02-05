package com.ikadev.daybydayplanner.service;

import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import com.ikadev.daybydayplanner.persistence.repository.DayEntryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EntryServiceTest {

    DayEntryRepository dayEntryRepository = mock(DayEntryRepository.class);
    EntryService entryService = new EntryService(dayEntryRepository);

    String username = "test";

    @Test
    void getAllEntriesForUser() {
        entryService.getAllEntriesForUser(username);

        verify(dayEntryRepository).findByUserUsername(username);
    }

    @Test
    void getEntryForUserAndDate() {
        LocalDate date = LocalDate.now();

        entryService.getEntryForUserAndDate(date, username);

        verify(dayEntryRepository).findByUserUsernameAndDate(username, date);
    }

    @Test
    void saveEntry() {
        DayEntry dayEntry = DayEntry.builder().build();

        entryService.saveEntry(dayEntry);

        verify(dayEntryRepository).save(dayEntry);
    }
}