package org.example.javalab8.service;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.CourseDTO;
import org.example.javalab8.mapper.CourseMapper;
import org.example.javalab8.mapper.CourseMapperImpl;
import org.example.javalab8.model.Course;
import org.example.javalab8.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseMapperImpl courseMapperImpl;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAllOptimized()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.toDto(course);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDto(savedCourse);
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

//    private final JdbcTemplate jdbcTemplate;
//
//    public Integer countStudentByCourseId(Integer courseId) {
//        String sql = "Select COUNT(*) FROM course_students WHERE course_id = ?";
//        return jdbcTemplate.queryForObject(sql, Integer.class, courseId);
//    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<CourseDTO> getAllCoursesNPlusOne() {
        return courseRepository.findAllNPlusOne().stream().map(courseMapper::toDto).toList();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Page<CourseDTO> getCoursesPaginated(Pageable pageable) {
        return courseRepository.findAllPageable(pageable)
                .map(courseMapper::toDto);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateCoursePrice(Integer id, Double newPrice) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.setPrice(newPrice);
        courseRepository.save(course);
    }
}