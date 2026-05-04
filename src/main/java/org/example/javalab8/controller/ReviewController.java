package org.example.javalab8.controller;

import org.example.javalab8.model.Review;
import org.example.javalab8.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> getAll() {
        return service.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getById(@PathVariable int id) {
        return service.getReviewById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Review review) {
        service.createReview(review);
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id, @RequestBody Review incoming) {
        Review existing = service.getReviewById(id);
        if (incoming.getRating() != null) existing.setRating(incoming.getRating());
        if (incoming.getComment() != null) existing.setComment(incoming.getComment());
        if (incoming.getAuthor() != null) existing.setAuthor(incoming.getAuthor());
        service.updateReview(id, existing);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Review review) {
        service.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.deleteReview(id);
    }
}