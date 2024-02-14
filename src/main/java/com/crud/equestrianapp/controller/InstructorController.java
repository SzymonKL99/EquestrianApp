package com.crud.equestrianapp.controller;

import com.crud.equestrianapp.domain.Instructor;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.exceptions.InstructorNotFoundException;
import com.crud.equestrianapp.service.InstructorService;
import com.crud.equestrianapp.service.dto.InstructorDto;
import com.crud.equestrianapp.service.dto.LessonDto;
import com.crud.equestrianapp.service.mapper.InstructorMapper;
import com.crud.equestrianapp.service.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/instructors")
public class InstructorController {

    private final InstructorMapper instructorMapper;
    private final InstructorService instructorService;
    private final LessonMapper lessonMapper;

    @GetMapping
    public ResponseEntity<List<InstructorDto>> getInstructors() {
        List<Instructor> instructorList = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructorMapper.mapToInstructorDtoList(instructorList));
    }

    @GetMapping(value = "/{instructorId}")
    public ResponseEntity<InstructorDto> getInstructor(@PathVariable Long instructorId) throws InstructorNotFoundException {
        return ResponseEntity.ok(instructorMapper.mapToInstructorDto(instructorService.getInstructor(instructorId)));
    }

    @GetMapping(value = "/{instructorId}/lessons")
    public ResponseEntity<List<LessonDto>> getLessonsByInstructorId(@PathVariable Long instructorId) throws InstructorNotFoundException {
        List<Lesson> lessonsByInstructorId = instructorService.getLessonsByInstructorId(instructorId);
        return ResponseEntity.ok(lessonMapper.mapToLessonDtoList(lessonsByInstructorId));
    }

    @PostMapping
    public ResponseEntity<Void> createInstructor(@RequestBody InstructorDto instructorDto) {
        Instructor instructor = instructorMapper.mapToInstructor(instructorDto);
        instructorService.saveInstructor(instructor);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{instructorId}")
    public ResponseEntity<InstructorDto> updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorDto instructorDto) throws InstructorNotFoundException {
        Instructor instructor = instructorMapper.mapToInstructor(instructorDto);
        Instructor savedInstructor = instructorService.updateInstructor(instructor);
        return ResponseEntity.ok(instructorMapper.mapToInstructorDto(savedInstructor));
    }

    @DeleteMapping(value = "/{instructorId}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long instructorId) throws InstructorNotFoundException {
        instructorService.deleteInstructor(instructorId);
        return ResponseEntity.noContent().build();
    }
}