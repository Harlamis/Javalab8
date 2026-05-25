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
import org.example.javalab8.dto.ReviewDTO;
import org.example.javalab8.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "API for handling user feedback, scores, and course reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Get all reviews", description = "Retrieves an unstructured list of all customer reviews from the database.")
    @ApiResponse(responseCode = "200", description = "Successfully fetched all available reviews")
    @GetMapping
    public List<ReviewDTO> getAll() {
        return reviewService.getAllReviews();
    }

    @Operation(summary = "Get review by ID", description = "Finds a specific review and returns its ranking score along with content details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found successfully"),
            @ApiResponse(responseCode = "404", description = "Review with the specified ID does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ReviewDTO getById(
            @Parameter(description = "Unique identifier of the review", example = "1") @PathVariable Integer id) {
        return reviewService.getReviewById(id);
    }

    @Operation(summary = "Get paginated reviews", description = "Fetches a specific page of reviews. Utilizes fetch joins to bind author and course information safely.")
    @ApiResponse(responseCode = "200", description = "Requested reviews page successfully populated")
    @GetMapping("/page")
    public Page<ReviewDTO> getAllPaginated(
            @Parameter(description = "Zero-based page index", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "The size of the page to be returned", example = "10") @RequestParam(defaultValue = "10") int size) {
        return reviewService.getReviewsPaginated(PageRequest.of(page, size));
    }

    @Operation(summary = "Create a new review", description = "Submits a new course review with validation rules controlling rating limits.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review recorded successfully"),
            @ApiResponse(responseCode = "400", description = "Rating scale boundaries breached or content missing", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ReviewDTO create(
            @Parameter(description = "Review rating score and textual remarks") @Valid @RequestBody ReviewDTO dto) {
        return reviewService.createReview(dto);
    }

    @Operation(summary = "Delete a review", description = "Deletes a specified review record permanently from the persistence layer.")
    @ApiResponse(responseCode = "200", description = "Review record dropped successfully")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "Unique identifier of the review to delete", example = "1") @PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}