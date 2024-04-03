package com.crud.equestrianapp.service;

import com.crud.equestrianapp.domain.Client;
import com.crud.equestrianapp.domain.Instructor;
import com.crud.equestrianapp.domain.Lesson;
import com.crud.equestrianapp.domain.NotificationOfLessonEnrolment;
import com.crud.equestrianapp.exceptions.LessonNotFoundException;
import com.crud.equestrianapp.repository.ClientRepository;
import com.crud.equestrianapp.repository.InstructorRepository;
import com.crud.equestrianapp.repository.LessonRepository;
import com.crud.equestrianapp.repository.NotificationOfLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LessonService {

    private final ClientRepository clientRepository;
    private final LessonRepository lessonRepository;
    private final InstructorRepository instructorRepository;
    private final NotificationOfLessonRepository notificationOfLessonRepository;

    public void saveLesson(final Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLesson(Long id) throws LessonNotFoundException {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);

        if (lessonOptional.isEmpty()) {
            throw new LessonNotFoundException("Lesson with id: " + id + "not found");
        }
        return lessonOptional.get();
    }

    public Lesson updateLesson(final Lesson lesson) throws LessonNotFoundException {
        Optional<Lesson> optionalUpdatedLesson = lessonRepository.findById(lesson.getId());

        if (optionalUpdatedLesson.isEmpty()) {
            throw new LessonNotFoundException("Lesson with id: " + lesson.getId() + "not found");
        }

        Lesson updatedLesson = optionalUpdatedLesson.get();
        lesson.setPayment(updatedLesson.getPayment());
        lesson.setClients(updatedLesson.getClients());
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) throws LessonNotFoundException {
        Optional<Lesson> lessonToDelete = lessonRepository.findById(id);

        if (lessonToDelete.isEmpty()) {
            throw new LessonNotFoundException("Lesson with id: " + id + "not found");
        }

        Lesson lesson = lessonToDelete.get();
        List<Instructor> instructors = lesson.getAssignedInstructors();
        instructors.forEach(instructor -> {
            instructor.getAssignedLessons().remove(lesson);
            instructorRepository.save(instructor);
        });
        lesson.setAssignedInstructors(new ArrayList<>());
        lesson.setAssignedHorses(new ArrayList<>());
        lesson.setClients(new ArrayList<>());
        lessonRepository.save(lesson);
        lessonRepository.deleteById(id);
    }

    @Transactional
    public void addClientToLesson(Long lessonId, Long clientId) {
        Client client = clientRepository.findById(clientId).get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        List<Lesson> clientLesson = client.getLessonsList();
        clientLesson.add(lesson);
        List<Client> lessonClient = lesson.getClients();
        lessonClient.add(client);
        clientRepository.save(client);
        lessonRepository.save(lesson);
        NotificationOfLessonEnrolment notification = new NotificationOfLessonEnrolment(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhone(),
                client.getEmail(),
                lesson.getDescription()
        );
        notificationOfLessonRepository.save(notification);
    }
}
