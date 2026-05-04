package org.example.javalab8.mapper;

import org.example.javalab8.dto.CourseDTO;
import org.example.javalab8.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "instructor.name", target = "instructorName")
    CourseDTO toDto(Course course);

    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "students", ignore = true)
    Course toEntity(CourseDTO courseDTO);
}