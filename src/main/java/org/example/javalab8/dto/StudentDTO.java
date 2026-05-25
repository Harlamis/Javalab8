package org.example.javalab8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentDTO {
    @Schema(description = "Unique ID")
    private Integer id;

    @Schema(description = "Name of the Student", example = "John Doe")
    @NotBlank(message = "Student name is required")
    @Size(min = 2, message = "Name is too short")
    private String name;

    @Schema(description = "Email address of the Student", example = "johndoe@gmail.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(description = "Date and time of Student's registration", example = "2026-05-25T14:30:00")
    private LocalDateTime registrationDate;
}