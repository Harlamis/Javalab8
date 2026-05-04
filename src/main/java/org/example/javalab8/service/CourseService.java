package org.example.javalab8.service;

import org.example.javalab8.model.Course;
import org.example.javalab8.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course getCourseById(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public void createCourse(Course course) {
        var courses = repository.findAll();
        boolean foundDuplicate = courses.stream()
                .anyMatch(c -> c.getName().equals(course.getName()));
        if (foundDuplicate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course with such name already exists!");
        }
        repository.save(course);
    }

    public void updateCourse(int id, Course course) {
        getCourseById(id);
        course.setId(id);
        repository.save(course);
    }

    public void deleteCourse(int id) {
        repository.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }


}
