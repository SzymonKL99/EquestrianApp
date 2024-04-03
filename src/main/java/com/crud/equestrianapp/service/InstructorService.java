package com.crud.equestrianapp.service;

import com.crud.equestrianapp.domain.Instructor;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.exceptions.InstructorNotFoundException;
import com.crud.equestrianapp.repository.InstructorRepository;
import com.crud.equestrianapp.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final LessonRepository lessonRepository;

    public void saveInstructor(final Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public Instructor getInstructor(Long id) throws InstructorNotFoundException {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);

        if (instructorOptional.isEmpty()) {
            throw new InstructorNotFoundException("Instructor with id " + id + " not found");
        }

        return instructorOptional.get();
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor updateInstructor(final Instructor instructor) throws InstructorNotFoundException {
        Optional<Instructor> optionalUpdatedInstructor = instructorRepository.findById(instructor.getId());

        if (optionalUpdatedInstructor.isEmpty()) {
            throw new InstructorNotFoundException("Instructor with id " + instructor.getId() + " not found");
        }

        Instructor updatedInstructor = optionalUpdatedInstructor.get();
        instructor.setAssignedLessons(updatedInstructor.getAssignedLessons());

        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) throws InstructorNotFoundException {
        Optional<Instructor> instructorToDelete = instructorRepository.findById(id);

        if (instructorToDelete.isEmpty()) {
            throw new InstructorNotFoundException("Instructor with id " + id + " not found");
        }

        Instructor instructor = instructorToDelete.get();
        List<Lesson> lessons = instructor.getAssignedLessons();
        lessons.forEach(lesson -> {
            lesson.getAssignedInstructors().remove(instructor);
            lessonRepository.save(lesson);
        });
        instructor.setAssignedLessons(new ArrayList<>());
        instructorRepository.save(instructor);
        instructorRepository.deleteById(id);
    }

    public List<Lesson> getLessonsByInstructorId(Long id) throws InstructorNotFoundException {
        Instructor instructor = getInstructor(id);
        if (instructor == null) {
            throw new InstructorNotFoundException("Instructor with" + id + "not found");
        }
        return instructor.getAssignedLessons();
    }
}