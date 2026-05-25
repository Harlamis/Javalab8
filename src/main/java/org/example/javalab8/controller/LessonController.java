package org.example.javalab8.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.ErrorResponse;
import org.example.javalab8.dto.LessonDTO;
import org.example.javalab8.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
@Tag(name = "Lessons", description = "API for managing individual course lessons and study content")
public class LessonController {

    private final LessonService lessonService;

    @Operation(summary = "Get all lessons", description = "Retrieves a flat list of all lessons across all available modules.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all lessons")
    @GetMapping
    public List<LessonDTO> getAll() {
        return lessonService.getAllLessons();
    }

    @Operation(summary = "Get lesson by ID", description = "Retrieves educational data and contents of a lesson using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lesson record found successfully"),
            @ApiResponse(responseCode = "404", description = "Lesson record not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public LessonDTO getById(
            @Parameter(description = "Unique identifier of the lesson", example = "1") @PathVariable Integer id) {
        return lessonService.getLessonById(id);
    }

    @Operation(summary = "Get paginated lessons", description = "Retrieves lessons with pagination using optimized relational queries to fetch associated metadata.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the requested page of lessons")
    @GetMapping("/page")
    public Page<LessonDTO> getAllPaginated(
            @Parameter(description = "Zero-based page index", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "The size of the page to be returned", example = "10") @RequestParam(defaultValue = "10") int size) {
        return lessonService.getLessonsPaginated(PageRequest.of(page, size));
    }

    @Operation(summary = "Create a new lesson", description = "Creates and links a new lesson to a specified course. Validates the incoming context.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lesson created and attached successfully"),
            @ApiResponse(responseCode = "400", description = "Validation parameters violated", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public LessonDTO create(
            @Parameter(description = "Lesson data and structural configurations") @Valid @RequestBody LessonDTO dto) {
        return lessonService.createLesson(dto);
    }

    @Operation(summary = "Delete a lesson", description = "Removes a specific lesson from the database using its unique identifier.")
    @ApiResponse(responseCode = "200", description = "Lesson successfully removed")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "Unique identifier of the lesson to delete", example = "1") @PathVariable Integer id) {
        lessonService.deleteLesson(id);
    }
}