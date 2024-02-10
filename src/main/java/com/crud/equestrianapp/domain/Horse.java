package com.crud.equestrianapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "HORSES")
public class Horse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horse_name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "assignedHorses")
    private List<Lesson> assignedLessons = new ArrayList<>();

    public Horse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedLessons = new ArrayList<>();
    }
}
