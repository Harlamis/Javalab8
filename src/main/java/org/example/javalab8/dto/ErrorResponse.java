package org.example.javalab8.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@Schema(description = "Object that is returned in case of error (for example: validation error")
public class ErrorResponse {

    @Schema(description = "Timestamp of error's occurrence", example = "2026-05-25T15:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error type", example = "Bad Request")
    private String error;

    @Schema(description = "Error description", example = "Validation failed")
    private String message;

    @Schema(description = "List of fields that haven't passed the validation",
            example = "{\"price\": \"The price must be greater than 0\", \"name\": \"Course name cannot be null\"}")
    private Map<String, String> validationErrors;
}