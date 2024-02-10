package com.crud.equestrianapp.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "LESSONS")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_lesson")
    private int pricePerLesson;


    @ManyToMany
    @JoinTable(
            name = "LESSONS_TO_HORSES",
            joinColumns = {@JoinColumn(name = "lesson_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "horse_id", referencedColumnName = "id")}
    )
    private List<Horse> assignedHorses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "LESSONS_TO_INSTRUCTORS",
            joinColumns = {@JoinColumn(name = "lesson_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "instructor_id", referencedColumnName = "id")}
    )
    private List<Instructor> assignedInstructors = new ArrayList<>();

    @ManyToMany(mappedBy = "lessonsList")
    private List<Client> clients;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "lesson_id")
    private List<Payment> payment = new ArrayList<>();

    @Column(name = "start_lesson")
    private LocalDateTime startLesson;

    @Column(name = "lesson_duration")
    private int lessonDuration;



}