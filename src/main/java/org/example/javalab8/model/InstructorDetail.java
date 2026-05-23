package org.example.javalab8.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "instructor_detail")
@Data
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bio;

    @Column(name = "social_media")
    private String socialMedia;
}