package org.example.javalab8.controller;

import org.example.javalab8.model.Lesson;
import org.example.javalab8.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lesson> getAll() {
        return service.getAllLessons();
    }

    @GetMapping("/{id}")
    public Lesson getById(@PathVariable int id) {
        return service.getLessonById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Lesson lesson) {
        service.createLesson(lesson);
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id, @RequestBody Lesson incoming) {
        Lesson existing = service.getLessonById(id);
        if (incoming.getTitle() != null) existing.setTitle(incoming.getTitle());
        if (incoming.getContent() != null) existing.setContent(incoming.getContent());
        if (incoming.getCourse() != null) existing.setCourse(incoming.getCourse());
        service.updateLesson(id, existing);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Lesson lesson) {
        service.updateLesson(id, lesson);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.deleteLesson(id);
    }
}