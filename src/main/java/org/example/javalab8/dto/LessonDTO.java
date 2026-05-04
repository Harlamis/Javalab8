package org.example.javalab8.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LessonDTO {
    private Integer id;

    @NotBlank(message = "Lesson title is required")
    private String title;

    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "Course ID must be specified")
    private Integer courseId;
}