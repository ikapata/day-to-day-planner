package com.ikadev.daybydayplanner.service;

import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import com.ikadev.daybydayplanner.persistence.model.Mood;
import com.ikadev.daybydayplanner.persistence.repository.DayEntryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EntryServiceTest {

    DayEntryRepository dayEntryRepository = mock(DayEntryRepository.class);
    UserService userService = mock(UserService.class);
    EntryService entryService = new EntryService(dayEntryRepository, userService);

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

        entryService.saveEntry(dayEntry, username);

        verify(dayEntryRepository).save(dayEntry);
    }


    @Test
    void getMoodsForYear() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        Mood mood = Mood.OK;
        DayEntry dayEntry = DayEntry.builder().mood(mood).date(localDate).build();
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        when(dayEntryRepository.findByUserUsernameAndDateBetween(username, firstDayOfYear, firstDayOfYear.plusYears(1)))
                .thenReturn(List.of(dayEntry));
        entryService.saveEntry(dayEntry, username);
        Map<Mood, List<LocalDate>> expected = Map.of(
                Mood.TERRIBLE, List.of(),
                Mood.BAD, List.of(),
                Mood.OK, List.of(localDate),
                Mood.GOOD, List.of(),
                Mood.GREAT, List.of()
        );

        Map<Mood, List<LocalDate>> result = entryService.getMoodsForYear(year, username);

        assertEquals(expected, result);
    }

}