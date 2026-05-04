package org.example.javalab8.mapper;

import org.example.javalab8.dto.LessonDTO;
import org.example.javalab8.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    @Mapping(source = "course.id", target = "courseId")
    LessonDTO toDto(Lesson lesson);

    @Mapping(target = "course", ignore = true)
    Lesson toEntity(LessonDTO dto);
}