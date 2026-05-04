package org.example.javalab8.controller;

import org.example.javalab8.model.Instructor;
import org.example.javalab8.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Instructor> getAll() {
        return service.getAllInstructors();
    }

    @GetMapping("/{id}")
    public Instructor getById(@PathVariable int id) {
        return service.getInstructorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Instructor instructor) {
        service.createInstructor(instructor);
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id, @RequestBody Instructor incoming) {
        Instructor existing = service.getInstructorById(id);
        if (incoming.getName() != null) existing.setName(incoming.getName());
        if (incoming.getExperience() != null) existing.setExperience(incoming.getExperience());
        if (incoming.getSpeciality() != null) existing.setSpeciality(incoming.getSpeciality());
        service.updateInstructor(id, existing);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Instructor instructor) {
        service.updateInstructor(id, instructor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.deleteInstructor(id);
    }
}