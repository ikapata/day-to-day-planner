package com.ikadev.daybydayplanner.persistence.repository;

import com.ikadev.daybydayplanner.persistence.model.DayEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "dayentryrepository", path = "entries")
public interface DayEntryRepository extends JpaRepository<DayEntry, Long> {

}
