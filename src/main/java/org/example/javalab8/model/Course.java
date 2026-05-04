package org.example.javalab8.model;

import lombok.Data;

@Data
public class Course {
    private Integer id;
    private String name;
    private String description;
    private Integer length;
    private Double price;
}
