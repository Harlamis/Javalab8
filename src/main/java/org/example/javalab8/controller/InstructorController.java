package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.InstructorDTO;
import org.example.javalab8.service.InstructorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/{id}")
    public InstructorDTO getById(@PathVariable Integer id) {
        return instructorService.getInstructorById(id);
    }

    @GetMapping("/page")
    public Page<InstructorDTO> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return instructorService.getInstructorsPaginated(PageRequest.of(page, size));
    }

    @PostMapping
    public InstructorDTO create(@Valid @RequestBody InstructorDTO dto) {
        return instructorService.createInstructor(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        instructorService.deleteInstructor(id);
    }
}