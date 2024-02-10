package com.crud.equestrianapp.service.mapper;

import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.service.dto.LessonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonMapper {

    @Autowired
    private HorseMapper horseMapper;

    @Autowired
    private InstructorMapper instructorMapper;

    public Lesson mapToLesson(final LessonDto lessonDto) {
        return new Lesson(
                lessonDto.getId(),
                lessonDto.getDescription(),
                lessonDto.getPricePerLesson(),
                horseMapper.mapToHorseList(lessonDto.getAssignedHorses()),
                instructorMapper.mapToInstructorList(lessonDto.getAssignedInstructors()),
                List.of(),
                List.of(),
                lessonDto.getStartLesson(),
                lessonDto.getLessonDuration()

        );
    }

    public LessonDto mapToLessonDto(final Lesson lesson) {
        return new LessonDto(
                lesson.getId(),
                lesson.getDescription(),
                lesson.getPricePerLesson(),
                instructorMapper.mapToInstructorDtoList(lesson.getAssignedInstructors()),
                horseMapper.mapToHorseDtoList(lesson.getAssignedHorses()),
                lesson.getStartLesson(),
                lesson.getLessonDuration()
        );
    }

    public List<LessonDto> mapToLessonDtoList(final List<Lesson> lessonList) {
        return lessonList.stream()
                .map(this::mapToLessonDto)
                .collect(Collectors.toList());
    }


}
