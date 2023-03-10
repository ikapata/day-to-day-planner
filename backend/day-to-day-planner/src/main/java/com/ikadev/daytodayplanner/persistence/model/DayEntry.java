package com.ikadev.daytodayplanner.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ToDoEntry> toDoList;

    private String diaryEntry;

    private Mood mood;

}
