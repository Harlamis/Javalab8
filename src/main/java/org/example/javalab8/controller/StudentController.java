package org.example.javalab8.controller;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.StudentDTO;
import org.example.javalab8.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return studentService.getAllStudents();
    }

    @PostMapping("/register")
    public StudentDTO register(@RequestBody StudentDTO dto) {
        return studentService.registerStudent(dto);
    }
}