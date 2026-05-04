package org.example.javalab8.repository;

import org.example.javalab8.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private int currentId = 1;
    private final List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return students;
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public void save(Student student) {
        if (student.getId() == null) {
            student.setId(currentId);
            students.add(student);
            currentId++;
            return;
        }
        Optional<Student> match = findById(student.getId());
        if (match.isPresent()) {
            int index = students.indexOf(match.get());
            students.set(index, student);
        }
    }

    public Optional<Student> deleteById(int id) {
        Optional<Student> match = findById(id);
        if (match.isPresent()) {
            students.remove(match.get());
            return match;
        }
        return Optional.empty();
    }
}
