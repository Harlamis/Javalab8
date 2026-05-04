package org.example.javalab8.mapper;

import org.example.javalab8.dto.ReviewDTO;
import org.example.javalab8.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "course.name", target = "courseName")
    ReviewDTO toDto(Review review);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "course", ignore = true)
    Review toEntity(ReviewDTO dto);
}