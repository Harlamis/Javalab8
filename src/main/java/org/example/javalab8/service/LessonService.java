package org.example.javalab8.service;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.LessonDTO;
import org.example.javalab8.mapper.LessonMapper;
import org.example.javalab8.model.Course;
import org.example.javalab8.model.Lesson;
import org.example.javalab8.repository.CourseRepository;
import org.example.javalab8.repository.LessonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    public LessonDTO addLessonToCourse(LessonDTO dto) {
        // Шукаємо курс, до якого належить лекція
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Lesson lesson = lessonMapper.toEntity(dto);
        lesson.setCourse(course); // Встановлюємо зв'язок

        return lessonMapper.toDto(lessonRepository.save(lesson));
    }
}