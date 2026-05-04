package org.example.javalab8.controller;

import org.example.javalab8.model.Course;
import org.example.javalab8.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        return service.getCourseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Course course) {
        service.createCourse(course);
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id,@RequestBody Course incoming) {
        Course existing = service.getCourseById(id);
        if (incoming.getName() != null) existing.setName(incoming.getName());
        if (incoming.getDescription() != null) existing.setDescription(incoming.getDescription());
        if (incoming.getLength() != null) existing.setLength(incoming.getLength());
        if (incoming.getPrice() != null) existing.setPrice(incoming.getPrice());
        service.updateCourse(id, existing);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Course course) {
        service.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.deleteCourse(id);
    }


}
