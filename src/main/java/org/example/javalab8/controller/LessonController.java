package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.LessonDTO;
import org.example.javalab8.service.LessonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public LessonDTO addLesson(@Valid @RequestBody LessonDTO dto) {
        return lessonService.addLessonToCourse(dto);
    }
}