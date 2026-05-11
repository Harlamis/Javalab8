package org.example.javalab8.repository;

import org.example.javalab8.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c JOIN FETCH c.instructor")
    List<Course> findAllOptimized();
}