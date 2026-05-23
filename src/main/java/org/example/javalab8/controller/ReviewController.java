package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.ReviewDTO;
import org.example.javalab8.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> getAll() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDTO getById(@PathVariable Integer id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/page")
    public Page<ReviewDTO> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return reviewService.getReviewsPaginated(PageRequest.of(page, size));
    }

    @PostMapping
    public ReviewDTO create(@Valid @RequestBody ReviewDTO dto) {
        return reviewService.createReview(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}