package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.CourseDTO;
import org.example.javalab8.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAll() {
        return courseService.getAllCourses();
    }

    @GetMapping("/n-plus-one-demo")
    public List<CourseDTO> getNPlusOneDemo() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public CourseDTO create(@Valid @RequestBody CourseDTO dto) {
        return courseService.createCourse(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}