package com.ikadev.daytodayplanner.persistence.repository;

import com.ikadev.daytodayplanner.persistence.model.DayEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface DayEntryRepository extends JpaRepository<DayEntry, Long> {

    Optional<DayEntry> findByUserUsernameAndDate(String username, LocalDate date);
    List<DayEntry> findByUserUsernameAndDateBetween(String username, LocalDate date1, LocalDate date2);
    List<DayEntry> findByUserUsername(String username);
}
