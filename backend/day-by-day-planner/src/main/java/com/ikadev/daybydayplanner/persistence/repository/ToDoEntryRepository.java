package com.ikadev.daybydayplanner.persistence.repository;

import com.ikadev.daybydayplanner.persistence.model.ToDoEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoEntryRepository extends JpaRepository<ToDoEntry, Long> {
}
