package com.crud.equestrianapp.testMapper;

import com.crud.equestrianapp.domain.Horse;
import com.crud.equestrianapp.domain.Instructor;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.service.dto.HorseDto;
import com.crud.equestrianapp.service.dto.InstructorDto;
import com.crud.equestrianapp.service.dto.LessonDto;
import com.crud.equestrianapp.service.mapper.LessonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LessonMapperTest {

    @Autowired
    LessonMapper lessonMapper;

    @Test
    void testMapToLesson() {
        //Given
        InstructorDto instructorDto = new InstructorDto(1L, "Adam", "Klon",
                "Hello, I'm Adam...");
        List<InstructorDto> instructorDtoList = new ArrayList<>();
        instructorDtoList.add(instructorDto);

        HorseDto horseDto = new HorseDto(1l, "Storm", "Storm is a ...");
        List<HorseDto> horseDtoList = new ArrayList<>();
        horseDtoList.add(horseDto);

        LocalDateTime startLesson = LocalDateTime.of(2024, 4, 22, 10, 0);

        LessonDto lessonDto = new LessonDto(1L, "Advanced section", 100, instructorDtoList,
                horseDtoList, startLesson, 60);

        //When
        Lesson lesson = lessonMapper.mapToLesson(lessonDto);

        //Then
        assertEquals(lessonDto.getId(), lesson.getId());
        assertEquals(lessonDto.getDescription(), lesson.getDescription());
        assertEquals(lessonDto.getPricePerLesson(), lesson.getPricePerLesson());
        assertEquals(instructorDtoList.size(), lesson.getAssignedInstructors().size());
        assertEquals(horseDtoList.size(), lesson.getAssignedHorses().size());
        assertEquals(startLesson, lesson.getStartLesson());
        assertEquals(lessonDto.getLessonDuration(), lesson.getLessonDuration());

    }

    @Test
    void testMapToLessonDto() {
        // Given
        List<Instructor> assignedInstructors = new ArrayList<>();
        assignedInstructors.add(new Instructor(1L, "Adam", "Klon", "Hello, I'm Adam..."));

        List<Horse> assignedHorses = new ArrayList<>();
        assignedHorses.add(new Horse(1L, "Storm", "Storm is a ..."));

        LocalDateTime startLesson = LocalDateTime.of(2024, 4, 22, 10, 0);

        Lesson lesson = new Lesson(1L, "Advanced section", 100, assignedHorses,
                assignedInstructors, List.of(), List.of(), startLesson, 60);

        // When
        LessonDto lessonDto = lessonMapper.mapToLessonDto(lesson);

        // Then
        assertEquals(lesson.getId(), lessonDto.getId());
        assertEquals(lesson.getDescription(), lessonDto.getDescription());
        assertEquals(lesson.getPricePerLesson(), lessonDto.getPricePerLesson());
        assertEquals(assignedInstructors.size(), lessonDto.getAssignedInstructors().size());
        assertEquals(assignedHorses.size(), lessonDto.getAssignedHorses().size());
        assertEquals(startLesson, lessonDto.getStartLesson());
        assertEquals(lesson.getLessonDuration(), lessonDto.getLessonDuration());
    }

    @Test
    void testMapToLessonDtoList() {
        // Given
        List<Lesson> lessonList = Arrays.asList(
                new Lesson(1L, "Lesson 1", 50, new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), LocalDateTime.now(), 60),
                new Lesson(2L, "Lesson 2", 60, new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), LocalDateTime.now(), 90)
        );

        // When
        List<LessonDto> lessonDtoList = lessonMapper.mapToLessonDtoList(lessonList);

        // Then
        assertEquals(lessonList.size(), lessonDtoList.size());
    }
}
