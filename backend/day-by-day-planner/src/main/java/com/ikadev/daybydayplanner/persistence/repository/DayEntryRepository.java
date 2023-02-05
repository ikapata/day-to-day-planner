package com.ikadev.daybydayplanner.persistence.repository;

import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface DayEntryRepository extends JpaRepository<DayEntry, Long> {

    Optional<DayEntry> findByUserUsernameAndDate(String username, LocalDate date);
    List<DayEntry> findByUserUsername(String username);
}
