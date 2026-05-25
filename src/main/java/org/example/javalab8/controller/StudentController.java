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
import org.example.javalab8.dto.StudentDTO;
import org.example.javalab8.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "API for handling student profiles, data validation, and registration entries")
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Get all students", description = "Retrieves an unfiltered list containing global student profile data structures.")
    @ApiResponse(responseCode = "200", description = "Global students listing delivered successfully")
    @GetMapping
    public List<StudentDTO> getAll() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Get student by ID", description = "Locates a student registry record by matching the provided unique key identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student account mapped successfully"),
            @ApiResponse(responseCode = "404", description = "No student matches the provided ID", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public StudentDTO getById(
            @Parameter(description = "Unique identifier of the student", example = "1") @PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Get paginated students", description = "Returns student profiles slice by slice to optimize memory consumption and transmission bandwidth.")
    @ApiResponse(responseCode = "200", description = "Target page segment mapped successfully")
    @GetMapping("/page")
    public Page<StudentDTO> getAllPaginated(
            @Parameter(description = "Zero-based page index", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "The size of the page to be returned", example = "10") @RequestParam(defaultValue = "10") int size) {
        return studentService.getStudentsPaginated(PageRequest.of(page, size));
    }

    @Operation(summary = "Register a new student", description = "Provisions a new student identity into the ecosystem. Validates correct layout rules for addresses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student profile saved and identity granted"),
            @ApiResponse(responseCode = "400", description = "Email structure invalid or obligatory elements missing", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public StudentDTO create(
            @Parameter(description = "Student credentials, tracking and personal layout elements") @Valid @RequestBody StudentDTO dto) {
        return studentService.createStudent(dto);
    }

    @Operation(summary = "Delete a student profile", description = "Removes a specific student profile entirely using their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Student account closed and dropped successfully")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "Unique identifier of the student profile to delete", example = "1") @PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}