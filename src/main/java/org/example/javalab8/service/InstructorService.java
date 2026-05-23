package org.example.javalab8.service;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.InstructorDTO;
import org.example.javalab8.mapper.InstructorMapper;
import org.example.javalab8.model.Instructor;
import org.example.javalab8.repository.InstructorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository.findAll()
                .stream()
                .map(instructorMapper::toDto)
                .collect(Collectors.toList());
    }

    public InstructorDTO getInstructorById(Integer id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        return instructorMapper.toDto(instructor);
    }

    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return instructorMapper.toDto(savedInstructor);
    }

    public void deleteInstructor(Integer id) {
        instructorRepository.deleteById(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Page<InstructorDTO> getInstructorsPaginated(Pageable pageable) {
        return instructorRepository.findAll(pageable)
                .map(instructorMapper::toDto);
    }
}