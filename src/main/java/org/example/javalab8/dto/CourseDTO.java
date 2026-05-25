package org.example.javalab8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "DTO for transferring data about Courses")
public class CourseDTO {

    @Schema(description = "Course's unique ID")
    private Integer id;

    @NotBlank(message = "Course name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Schema(description = "Course name", example = "Java crash-course", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "Description must have text")
    @Schema(description = "Course's description", example = "Beginner's guide for Java programming language")
    private String description;

    @Min(value = 1, message = "Length must be at least 1 hour")
    @Schema(description = "Length of the Course, in hours", example = "40")
    private Integer length;

    @Schema(description = "Price of the Course, in UAH", example = "5999.00")
    @Positive(message = "The price must be greater than 0")
    private Double price;

    @Schema(description = "name of the Instructor, who teaches this Course (this field is filled automatically)", example = "John Doe")
    private String instructorName;
}