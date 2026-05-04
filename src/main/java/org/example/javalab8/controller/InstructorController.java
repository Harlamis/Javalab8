package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.InstructorDTO;
import org.example.javalab8.service.InstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public List<InstructorDTO> getAll() {
        return instructorService.getAllInstructors();
    }

    @PostMapping
    public InstructorDTO create(@Valid @RequestBody InstructorDTO dto) {
        return instructorService.createInstructor(dto);
    }
}