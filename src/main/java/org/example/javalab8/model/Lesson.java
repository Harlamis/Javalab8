package org.example.javalab8.model;

import lombok.Data;

@Data
public class Lesson {
    private Integer id;
    private String title;
    private String content;
    private Course course;
}
