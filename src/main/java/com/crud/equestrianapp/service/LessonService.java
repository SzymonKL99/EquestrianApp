package com.crud.equestrianapp.service;

import com.crud.equestrianapp.repository.ClientRepository;
import com.crud.equestrianapp.repository.InstructorRepository;
import com.crud.equestrianapp.repository.LessonRepository;
import com.crud.equestrianapp.repository.NotificationOfLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class LessonService {

    private final ClientRepository clientRepository;
    private final LessonRepository lessonRepository;
    private final InstructorRepository instructorRepository;
    private final NotificationOfLessonRepository notificationOfLessonRepository;


}
