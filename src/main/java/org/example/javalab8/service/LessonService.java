package org.example.javalab8.service;

import lombok.RequiredArgsConstructor;
import org.example.javalab8.dto.LessonDTO;
import org.example.javalab8.mapper.LessonMapper;
import org.example.javalab8.model.Lesson;
import org.example.javalab8.repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<LessonDTO> getAllLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    public LessonDTO getLessonById(Integer id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        return lessonMapper.toDto(lesson);
    }

    public LessonDTO createLesson(LessonDTO lessonDTO) {
        Lesson lesson = lessonMapper.toEntity(lessonDTO);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toDto(savedLesson);
    }

    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Page<LessonDTO> getLessonsPaginated(Pageable pageable) {
        return lessonRepository.findAllWithRelations(pageable)
                .map(lessonMapper::toDto);
    }
}