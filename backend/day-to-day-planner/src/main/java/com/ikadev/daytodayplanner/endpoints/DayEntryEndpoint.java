package com.ikadev.daytodayplanner.endpoints;


import com.ikadev.daytodayplanner.persistence.model.DayEntry;
import com.ikadev.daytodayplanner.persistence.model.Mood;
import com.ikadev.daytodayplanner.service.EntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/entries")
public class DayEntryEndpoint {
    private final EntryService entryService;

    public DayEntryEndpoint(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public List<DayEntry> getEntries(Principal principal) {
        return entryService.getAllEntriesForUser(principal.getName());
    }

    @GetMapping(path = "{date}")
    public DayEntry getEntryForDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Principal principal) {
        return entryService.getEntryForUserAndDate(date, principal.getName()).orElse(null);
    }

    @GetMapping(path = "date-moods/{year}")
    public Map<Mood, List<LocalDate>> getMoodsForYear(@PathVariable("year") int year, Principal principal) {
        return entryService.getMoodsForYear(year, principal.getName());
    }

    @PostMapping
    public DayEntry saveEntry(@RequestBody DayEntry entry, Principal principal) {
        return entryService.saveEntry(entry, principal.getName());
    }
}
