package org.example.javalab8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "DTO for transferring data about lesson")
public class LessonDTO {
    @Schema(description = "Unique id")
    private Integer id;

    @NotBlank(message = "Lesson title is required")
    @Schema(description = "The title of a Lesson", example = "Exceptions in Java")
    private String title;

    @Schema(description = "detailed content of a Lesson", example ="Lesson: Java Exception Handling. 1. Hierarchy: Throwable -> Exception/Error. 2. Checked vs Unchecked exceptions. 3. Handling: try-catch-finally blocks. 4. Best practices: Throw early, catch late and always clean up resources using try-with-resources." )
    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @Schema(description = "Unique ID for Course, that the Lesson is attached to")
    @NotNull(message = "Course ID must be specified")
    private Integer courseId;
}