package org.example.javalab8.mapper;

import org.example.javalab8.dto.StudentDTO;
import org.example.javalab8.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);
}