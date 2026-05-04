package org.example.javalab8.repository;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
    private int currentId = 1;
    private final List<Course> courses = new ArrayList<>();

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(int id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void save(Course course) {
        if (course.getId() == null) {
            course.setId(currentId);
            courses.add(course);
            currentId++;
            return;
        }
        Optional<Course> match = findById(course.getId());
        if (match.isPresent()) {
            int index = courses.indexOf(match.get());
            courses.set(index, course);
        }
    }

    public Optional<Course> deleteById(int id) {
        Optional<Course> match = findById(id);
        if (match.isPresent()) {
            courses.remove(match.get());
            return match;
        }
        return Optional.empty();
    }
}
