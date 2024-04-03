package com.crud.equestrianapp.controller;

import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.service.LessonService;
import com.crud.equestrianapp.service.dto.LessonDto;
import com.crud.equestrianapp.service.mapper.ClientMapper;
import com.crud.equestrianapp.service.mapper.LessonMapper;
import com.crud.equestrianapp.service.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/lessons")
public class LessonController {

    private final LessonMapper lessonMapper;
    private final LessonService lessonService;
    private final PaymentMapper paymentMapper;
    private final ClientMapper clientMapper;

    //@GetMapping
    //public ResponseEntity<List<LessonDto>> getLessons() {
      //  List<Lesson> lessonList = lessonService.getAllLessn
    //}
}
