package org.example.javalab8.controller;

import org.example.javalab8.model.Student;
import org.example.javalab8.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Student student) {
        service.createStudent(student);
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id, @RequestBody Student incoming) {
        Student existing = service.getStudentById(id);
        if (incoming.getName() != null) existing.setName(incoming.getName());
        if (incoming.getEmail() != null) existing.setEmail(incoming.getEmail());
        service.updateStudent(id, existing);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Student student) {
        service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.deleteStudent(id);
    }
}