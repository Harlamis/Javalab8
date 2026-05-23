package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.StudentDTO;
import org.example.javalab8.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/page")
    public Page<StudentDTO> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return studentService.getStudentsPaginated(PageRequest.of(page, size));
    }

    @PostMapping
    public StudentDTO create(@Valid @RequestBody StudentDTO dto) {
        return studentService.createStudent(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}