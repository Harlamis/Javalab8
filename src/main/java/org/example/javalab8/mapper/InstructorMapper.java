package org.example.javalab8.mapper;

import org.example.javalab8.dto.InstructorDTO;
import org.example.javalab8.model.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    InstructorDTO toDto(Instructor instructor);
    Instructor toEntity(InstructorDTO dto);
}