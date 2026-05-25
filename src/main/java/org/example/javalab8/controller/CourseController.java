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
import org.example.javalab8.dto.CourseDTO;
import org.example.javalab8.dto.ErrorResponse;
import org.example.javalab8.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Tag(name = "Courses", description = "API for managing educational courses and diagnosing performance patterns")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Get all courses (Optimized)", description = "Retrieves a complete list of all available courses using fetch joins to prevent the N+1 performance issue.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the optimized course list")
    @GetMapping
    public List<CourseDTO> getAllNoNPlusOne() {
        return courseService.getAllCourses();
    }

    @Operation(summary = "Get all courses (N+1 Demo)", description = "Retrieves all courses while intentionally triggering multiple subsequent database queries for demonstration purposes.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the course list with N+1 query execution pattern")
    @GetMapping("/demo")
    public List<CourseDTO> getAllNPlusOne() {
        return courseService.getAllCoursesNPlusOne();
    }

    @Operation(summary = "Get course by ID", description = "Retrieves detailed information about a specific course using its unique database identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course record found successfully"),
            @ApiResponse(responseCode = "404", description = "Course record with the provided ID does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public CourseDTO getById(
            @Parameter(description = "Unique identifier of the target course", example = "1") @PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @Operation(summary = "Get paginated courses", description = "Retrieves a specific page segment of courses to optimize payload size and transmission speed.")
    @ApiResponse(responseCode = "200", description = "Successfully populated and delivered the requested page slice")
    @GetMapping("/page")
    public Page<CourseDTO> getAllPaginated(
            @Parameter(description = "Zero-based page index parameter", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Total element capacity threshold per page slice", example = "10") @RequestParam(defaultValue = "10") int size) {
        return courseService.getCoursesPaginated(PageRequest.of(page, size));
    }

    @Operation(summary = "Create a new course", description = "Registers a new educational course within the persistence layer. Validates mandatory constraints.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course instance instantiated and stored successfully"),
            @ApiResponse(responseCode = "400", description = "Validation parameters violated or malformed payload layout", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public CourseDTO create(
            @Parameter(description = "Course properties profile specification structure") @Valid @RequestBody CourseDTO dto) {
        return courseService.createCourse(dto);
    }

    @Operation(summary = "Update course price", description = "Modifies the pricing value of an existing course entry. Executes within an isolated transaction layer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course price property adjusted successfully"),
            @ApiResponse(responseCode = "404", description = "Target course identifier not resolved in the database", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}/price")
    public void updatePrice(
            @Parameter(description = "Unique identifier of the target course entry") @PathVariable Integer id,
            @Parameter(description = "New target pricing value to assign", example = "299.99") @RequestParam Double newPrice) {
        courseService.updateCoursePrice(id, newPrice);
    }

    @Operation(summary = "Delete a course record", description = "Permanently expels a specific course entry from the persistence database based on its unique key.")
    @ApiResponse(responseCode = "200", description = "Course record purged completely or was already missing")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "Unique identifier of the course record to drop", example = "1") @PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}