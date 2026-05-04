package org.example.javalab8.model;

import lombok.Data;

@Data
public class Review {
    private Integer id;
    private Float rating;
    private String comment;
    private Student author;
}
