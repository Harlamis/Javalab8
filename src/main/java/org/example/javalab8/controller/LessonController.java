package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.LessonDTO;
import org.example.javalab8.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public List<LessonDTO> getAll() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public LessonDTO getById(@PathVariable Integer id) {
        return lessonService.getLessonById(id);
    }

    @GetMapping("/page")
    public Page<LessonDTO> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return lessonService.getLessonsPaginated(PageRequest.of(page, size));
    }

    @PostMapping
    public LessonDTO create(@Valid @RequestBody LessonDTO dto) {
        return lessonService.createLesson(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        lessonService.deleteLesson(id);
    }
}