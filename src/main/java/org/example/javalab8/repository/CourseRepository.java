package org.example.javalab8.repository;

import org.example.javalab8.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c from Course  c")
    List<Course> findAllNPlusOne();

    @Query("SELECT c from Course c join fetch c.instructor")
    List<Course> findAll();

    @Query(value = "select c from Course c join fetch c.instructor",
            countQuery = "select count(c) from Course c")
    Page<Course> findAllPageable(Pageable pageable);

}