package com.crud.equestrianapp.controller;

import com.crud.equestrianapp.domain.Horse;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.exceptions.HorseNotFoundException;
import com.crud.equestrianapp.service.HorseService;
import com.crud.equestrianapp.service.dto.HorseDto;
import com.crud.equestrianapp.service.dto.LessonDto;
import com.crud.equestrianapp.service.mapper.HorseMapper;
import com.crud.equestrianapp.service.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/horses")
public class HorseController {

    private final HorseMapper horseMapper;
    private final HorseService horseService;
    private final LessonMapper lessonMapper;

    @GetMapping
    public ResponseEntity<List<HorseDto>> getHorses() {
        List<Horse> horseList = horseService.getAllHorses();
        return ResponseEntity.ok(horseMapper.mapToHorseDtoList(horseList));
    }
    @GetMapping(value = "/{horseId}")
    public ResponseEntity<HorseDto> getHorse(@PathVariable Long horseId) throws HorseNotFoundException {
        return ResponseEntity.ok(horseMapper.mapToHorseDto(horseService.getHorse(horseId)));
    }

    @GetMapping(value = "/{horseId}/lesson")
    public ResponseEntity<List<LessonDto>> getLessonsByHorseId(@PathVariable Long horseId) throws HorseNotFoundException {
        List<Lesson> lessonsByHorseId = horseService.getLessonByHorseId(horseId);
        return ResponseEntity.ok(lessonMapper.mapToLessonDtoList(lessonsByHorseId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createHorse(@RequestBody HorseDto horseDto) {
        Horse horse = horseMapper.mapToHorse(horseDto);
        horseService.saveHorse(horse);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/{horseId}")
    public ResponseEntity<HorseDto> updateHorse(@PathVariable Long horseId, @RequestBody HorseDto horseDto) throws
            HorseNotFoundException {
        Horse horse = horseMapper.mapToHorse(horseDto);
        Horse savedHorse = horseService.updateHorse(horse);
        return ResponseEntity.ok(horseMapper.mapToHorseDto(savedHorse));
    }

    @DeleteMapping(value = "/{horseId}")
    public ResponseEntity<Void> deleteHorse(@PathVariable Long horseId) throws HorseNotFoundException {
        horseService.deleteHorse(horseId);
        return ResponseEntity.noContent().build();
    }



}
