package com.crud.equestrianapp.service;

import com.crud.equestrianapp.domain.Horse;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.exceptions.HorseNotFoundException;
import com.crud.equestrianapp.repository.HorseRepository;
import com.crud.equestrianapp.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HorseService {

    private final HorseRepository horseRepository;
    private final LessonRepository lessonRepository;

    public void saveHorse(final Horse horse) {
        horseRepository.save(horse);
    }

    public Horse getHorse(Long id) throws HorseNotFoundException {
        Optional<Horse> horseOptional = horseRepository.findById(id);

        if (horseOptional.isEmpty()) {
            throw new HorseNotFoundException("Horse with id " + id + " not found");
        }

        return horseOptional.get();
    }

    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

    public Horse updateHorse(final Horse horse) throws HorseNotFoundException {
        Optional<Horse> optionalUpdatedHorse = horseRepository.findById(horse.getId());

        if (optionalUpdatedHorse.isEmpty()) {
            throw new HorseNotFoundException("Horse with id " + horse.getId() + " not found");
        }

        Horse updatedHorse = optionalUpdatedHorse.get();
        horse.setAssignedLessons(updatedHorse.getAssignedLessons());

        return horseRepository.save(horse);
    }

    public void deleteHorse(Long id) throws HorseNotFoundException {
        Optional<Horse> horseToDelete = horseRepository.findById(id);

        if (horseToDelete.isEmpty()) {
            throw new HorseNotFoundException("Horse with id " + id + " not found");
        }

        Horse horse = horseToDelete.get();
        List<Lesson> lessons = horse.getAssignedLessons();
            lessons.forEach(lesson -> {lesson.getAssignedHorses().remove(horse);
            lessonRepository.save(lesson);
        });
        horse.setAssignedLessons(new ArrayList<>());
        horseRepository.save(horse);
        horseRepository.deleteById(id);
    }

    public List<Lesson> getLessonByHorseId(Long id) throws HorseNotFoundException {
        Horse horse = getHorse(id);
        if (horse == null) {
            throw new HorseNotFoundException("Horse with" + id + "not found");
        }
        return horse.getAssignedLessons();
    }
}
