package org.example.javalab8.service;

import org.example.javalab8.model.Review;
import org.example.javalab8.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    public Review getReviewById(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    public void createReview(Review review) {
        repository.save(review);
    }

    public void updateReview(int id, Review review) {
        getReviewById(id);
        review.setId(id);
        repository.save(review);
    }

    public void deleteReview(int id) {
        repository.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }
}