package com.ikadev.daybydayplanner.endpoints;


import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import com.ikadev.daybydayplanner.persistence.model.User;
import com.ikadev.daybydayplanner.persistence.repository.UserRepository;
import com.ikadev.daybydayplanner.service.EntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/entries")
public class DayEntryEndpoint {
    private final EntryService entryService;
    private final UserRepository userRepository;

    public DayEntryEndpoint(EntryService entryService,
                            UserRepository userRepository) {
        this.entryService = entryService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<DayEntry> getEntries(Principal principal) {
        return entryService.getAllEntriesForUser(principal.getName());
    }

    @GetMapping(path="{date}")
    public DayEntry getEntryForDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Principal principal) {
        return entryService.getEntryForUserAndDate(date, principal.getName()).orElse(null);
    }

    @PostMapping
    public DayEntry saveEntry(@RequestBody DayEntry entry, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        entry.setUser(user);
        return entryService.saveEntry(entry);
    }
}
