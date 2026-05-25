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
import org.example.javalab8.dto.InstructorDTO;
import org.example.javalab8.service.InstructorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
@Tag(name = "Instructors", description = "API for managing course instructors and their profiles")
public class InstructorController {

    private final InstructorService instructorService;

    @Operation(summary = "Get all instructors", description = "Retrieves a complete list of all registered instructors.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of instructors")
    @GetMapping
    public List<InstructorDTO> getAll() {
        return instructorService.getAllInstructors();
    }

    @Operation(summary = "Get instructor by ID", description = "Retrieves detailed information about a specific instructor using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor found successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public InstructorDTO getById(
            @Parameter(description = "Unique identifier of the instructor", example = "1") @PathVariable Integer id) {
        return instructorService.getInstructorById(id);
    }

    @Operation(summary = "Get paginated instructors", description = "Retrieves a slice of instructors based on page number and size parameters.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list")
    @GetMapping("/page")
    public Page<InstructorDTO> getAllPaginated(
            @Parameter(description = "Zero-based page index", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "The size of the page to be returned", example = "10") @RequestParam(defaultValue = "10") int size) {
        return instructorService.getInstructorsPaginated(PageRequest.of(page, size));
    }

    @Operation(summary = "Create a new instructor", description = "Registers a new instructor profile in the system. Validates input properties.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor profile created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body or validation failed", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public InstructorDTO create(
            @Parameter(description = "Instructor data profile details") @Valid @RequestBody InstructorDTO dto) {
        return instructorService.createInstructor(dto);
    }

    @Operation(summary = "Delete an instructor", description = "Removes an instructor profile completely from the system using their unique ID.")
    @ApiResponse(responseCode = "200", description = "Instructor successfully deleted or did not exist")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "Unique identifier of the instructor to delete", example = "1") @PathVariable Integer id) {
        instructorService.deleteInstructor(id);
    }
}