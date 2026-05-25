package org.example.javalab8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewDTO {
    private Integer id;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    @NotNull(message = "Rating is mandatory")
    @Schema(description = "Rating of a Course, ranging from 1 to 5", example = "4.5")
    private Double rating;

    @NotBlank(message = "Comment is required")
    @Schema(description = "Comment, attached to a Review", example = "Very comprehensive lectures! attached notes are easy to read and use a a cheatsheet")
    private String comment;

    @Schema(description = "Name of the Author of a Review", example = "John Doe")
    private String authorName;

    @Schema(description = "Name of the Course, that Review is attached to", example = "Java 101")
    private String courseName;
}