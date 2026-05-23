package org.example.javalab8.repository;

import org.example.javalab8.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query(value = "SELECT l FROM Lesson l JOIN FETCH l.course",
            countQuery = "SELECT count(l) FROM Lesson l")
    Page<Lesson> findAllWithRelations(Pageable pageable);
}