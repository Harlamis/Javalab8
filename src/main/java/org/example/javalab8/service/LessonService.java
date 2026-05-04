package org.example.javalab8.service;

import org.example.javalab8.model.Lesson;
import org.example.javalab8.repository.LessonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository repository;

    public LessonService(LessonRepository repository) {
        this.repository = repository;
    }

    public List<Lesson> getAllLessons() {
        return repository.findAll();
    }

    public Lesson getLessonById(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));
    }

    public void createLesson(Lesson lesson) {
        var lessons = repository.findAll();
        boolean foundDuplicate = lessons.stream()
                .anyMatch(l -> l.getTitle().equals(lesson.getTitle()));
        if (foundDuplicate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson title already exists!");
        }
        repository.save(lesson);
    }

    public void updateLesson(int id, Lesson lesson) {
        getLessonById(id);
        lesson.setId(id);
        repository.save(lesson);
    }

    public void deleteLesson(int id) {
        repository.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));
    }
}