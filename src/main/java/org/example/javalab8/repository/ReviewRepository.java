package org.example.javalab8.repository;

import org.example.javalab8.model.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository {
    private int currentId = 1;
    private final List<Review> reviews = new ArrayList<>();

    public List<Review> findAll() {
        return reviews;
    }

    public Optional<Review> findById(int id) {
        return reviews.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public void save(Review review) {
        if (review.getId() == null) {
            review.setId(currentId);
            reviews.add(review);
            currentId++;
            return;
        }
        Optional<Review> match = findById(review.getId());
        if (match.isPresent()) {
            int index = reviews.indexOf(match.get());
            reviews.set(index, review);
        }
    }

    public Optional<Review> deleteById(int id) {
        Optional<Review> match = findById(id);
        if (match.isPresent()) {
            reviews.remove(match.get());
            return match;
        }
        return Optional.empty();
    }
}
