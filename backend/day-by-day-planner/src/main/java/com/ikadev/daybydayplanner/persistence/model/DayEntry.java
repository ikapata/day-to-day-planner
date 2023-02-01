package com.ikadev.daybydayplanner.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class DayEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Date date;
    @OneToMany
    private List<ToDoEntry> todoList;

    private String diaryEntry;

    private Mood mood;

}
