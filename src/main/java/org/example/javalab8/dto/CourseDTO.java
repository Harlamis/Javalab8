package org.example.javalab8.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CourseDTO {
    private Integer id;

    @NotBlank(message = "Course name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotEmpty(message = "Description must have text")
    private String description;

    @Min(value = 1, message = "Length must be at least 1 hour")
    private Integer length;

    @Positive(message = "The price must be greater than 0")
    private Double price;

    private String instructorName;
}