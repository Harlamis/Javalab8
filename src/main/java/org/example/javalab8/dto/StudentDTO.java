package org.example.javalab8.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentDTO {
    private Integer id;

    @NotBlank(message = "Student name is required")
    @Size(min = 2, message = "Name is too short")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    private LocalDateTime registrationDate;
}