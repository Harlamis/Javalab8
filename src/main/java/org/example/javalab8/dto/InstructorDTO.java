package org.example.javalab8.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InstructorDTO {
    private Integer id;

    @NotBlank(message = "Instructor name is mandatory")
    private String name;

    @NotBlank(message = "Speciality is required")
    private String speciality;

    @PositiveOrZero(message = "Experience cannot be negative")
    private Double experience;
}