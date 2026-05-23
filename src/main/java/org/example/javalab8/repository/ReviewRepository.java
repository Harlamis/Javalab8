package org.example.javalab8.repository;

import org.example.javalab8.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT r FROM Review r JOIN FETCH r.author JOIN FETCH r.course",
            countQuery = "SELECT count(r) FROM Review r")
    Page<Review> findAllWithRelations(Pageable pageable);
}