package org.example.javalab8.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewDTO {
    private Integer id;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    @NotNull(message = "Rating is mandatory")
    private Double rating;

    @NotBlank(message = "Comment is required")
    private String comment;

    private String authorName;
    private String courseName;
}