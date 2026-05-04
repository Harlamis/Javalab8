package org.example.javalab8.repository;

import org.example.javalab8.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorRepository {
    private int currentId = 1;
    private final List<Instructor> instructors = new ArrayList<>();

    public List<Instructor> findAll() {
        return instructors;
    }

    public Optional<Instructor> findById(int id) {
        return instructors.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public void save(Instructor instructor) {
        if (instructor.getId() == null) {
            instructor.setId(currentId);
            instructors.add(instructor);
            currentId++;
            return;
        }
        Optional<Instructor> match = findById(instructor.getId());
        if (match.isPresent()) {
            int index = instructors.indexOf(match.get());
            instructors.set(index, instructor);
        }
    }

    public Optional<Instructor> deleteById(int id) {
        Optional<Instructor> match = findById(id);
        if (match.isPresent()) {
            instructors.remove(match.get());
            return match;
        }
        return Optional.empty();
    }
}