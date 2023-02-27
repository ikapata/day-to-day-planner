package com.ikadev.daytodayplanner.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoEntry {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean checked;
}
