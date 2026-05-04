package org.example.javalab8.service;

import org.example.javalab8.model.Student;
import org.example.javalab8.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public void createStudent(Student student) {
        var students = repository.findAll();
        boolean foundDuplicate = students.stream()
                .anyMatch(s -> s.getEmail().equals(student.getEmail()));
        if (foundDuplicate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student with this email already exists!");
        }
        repository.save(student);
    }

    public void updateStudent(int id, Student student) {
        getStudentById(id);
        student.setId(id);
        repository.save(student);
    }

    public void deleteStudent(int id) {
        repository.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }
}