package org.example.javalab8.service;

import org.example.javalab8.model.Instructor;
import org.example.javalab8.repository.InstructorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository repository;

    public InstructorService(InstructorRepository repository) {
        this.repository = repository;
    }

    public List<Instructor> getAllInstructors() {
        return repository.findAll();
    }

    public Instructor getInstructorById(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found"));
    }

    public void createInstructor(Instructor instructor) {
        var instructors = repository.findAll();
        boolean foundDuplicate = instructors.stream()
                .anyMatch(i -> i.getName().equals(instructor.getName()));
        if (foundDuplicate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor already exists!");
        }
        repository.save(instructor);
    }

    public void updateInstructor(int id, Instructor instructor) {
        getInstructorById(id);
        instructor.setId(id);
        repository.save(instructor);
    }

    public void deleteInstructor(int id) {
        repository.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found"));
    }
}