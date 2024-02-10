package com.crud.equestrianapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "INSTRUCTORS")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "assignedInstructors")
    private List<Lesson> assignedLessons = new ArrayList<>();

    public Instructor(Long id, String firstName, String lastName, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.assignedLessons = new ArrayList<>();
    }
}
