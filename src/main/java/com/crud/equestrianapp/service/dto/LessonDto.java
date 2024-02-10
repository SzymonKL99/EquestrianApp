package com.crud.equestrianapp.service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class LessonDto {
    private Long id;
    private String description;
    private int pricePerLesson;
    private List<InstructorDto> assignedInstructors;
    private List<HorseDto> assignedHorses;
    private LocalDateTime startLesson;
    private int lessonDuration;
}
