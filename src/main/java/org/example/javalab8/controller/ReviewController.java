package org.example.javalab8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.ReviewDTO;
import org.example.javalab8.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewDTO leaveReview(@RequestParam Integer studentId,
                                 @RequestParam Integer courseId,
                                 @Valid @RequestBody ReviewDTO dto) {
        return reviewService.leaveReview(studentId, courseId, dto);
    }
}