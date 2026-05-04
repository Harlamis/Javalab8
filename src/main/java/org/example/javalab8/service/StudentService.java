package org.example.javalab8.service;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.StudentDTO;
import org.example.javalab8.mapper.StudentMapper;
import org.example.javalab8.model.Student;
import org.example.javalab8.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentDTO registerStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        student.setRegistrationDate(LocalDateTime.now());
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }
}