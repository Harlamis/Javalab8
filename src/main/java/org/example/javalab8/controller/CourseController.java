package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.CourseDTO;
import org.example.javalab8.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllNoNPlusOne() {
        return courseService.getAllCourses();
    }

    @GetMapping("/demo")
    public List<CourseDTO> getAllNPlusOne() { return  courseService.getAllCoursesNPlusOne();}

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/page")
    public Page<CourseDTO> getAllPaginated( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return courseService.getCoursesPaginated(PageRequest.of(page, size));
    }

    @PostMapping
    public CourseDTO create(@Valid @RequestBody CourseDTO dto) {
        return courseService.createCourse(dto);
    }

    @PatchMapping("/{id}/price")
    public void updatePrice(@PathVariable Integer id, @RequestParam Double newPrice) {
        courseService.updateCoursePrice(id, newPrice);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}