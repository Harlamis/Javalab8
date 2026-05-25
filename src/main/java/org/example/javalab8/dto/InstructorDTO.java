package org.example.javalab8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "DTO for transferring data about Instructor")
public class InstructorDTO {
    private Integer id;

    @NotBlank(message = "Instructor name is mandatory")
    @Schema(description = "Instructor's name", example = "John Doe")
    private String name;

    @NotBlank(message = "Speciality is required")
    @Schema(description = "Specialty of Instructor", example = "Computer Science")
    private String speciality;

    @Schema(description = "Amount of years of experience", example = "5.5")
    @PositiveOrZero(message = "Experience cannot be negative")
    private Double experience;
}