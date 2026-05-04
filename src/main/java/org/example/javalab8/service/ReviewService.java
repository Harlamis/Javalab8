package org.example.javalab8.service;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.ReviewDTO;
import org.example.javalab8.mapper.ReviewMapper;
import org.example.javalab8.model.Course;
import org.example.javalab8.model.Review;
import org.example.javalab8.model.Student;
import org.example.javalab8.repository.CourseRepository;
import org.example.javalab8.repository.ReviewRepository;
import org.example.javalab8.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDTO leaveReview(Integer studentId, Integer courseId, ReviewDTO dto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Review review = reviewMapper.toEntity(dto);
        review.setAuthor(student);
        review.setCourse(course);

        return reviewMapper.toDto(reviewRepository.save(review));
    }
}