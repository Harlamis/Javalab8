package org.example.javalab8.mapper;

import org.example.javalab8.dto.InstructorDTO;
import org.example.javalab8.model.Instructor;

public class ManualInstructorMapper {
    public static InstructorDTO toDto(Instructor entity) {
        if (entity == null) return null;

        InstructorDTO dto = new InstructorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSpeciality(entity.getSpeciality());
        dto.setExperience(entity.getExperience());
        return dto;
    }
}