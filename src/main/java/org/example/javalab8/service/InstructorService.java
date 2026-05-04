package org.example.javalab8.service;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.InstructorDTO;
import org.example.javalab8.mapper.InstructorMapper;
import org.example.javalab8.model.Instructor;
import org.example.javalab8.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository.findAll().stream()
                .map(instructorMapper::toDto)
                .collect(Collectors.toList());
    }

    public InstructorDTO createInstructor(InstructorDTO dto) {
        Instructor instructor = instructorMapper.toEntity(dto);
        return instructorMapper.toDto(instructorRepository.save(instructor));
    }
}