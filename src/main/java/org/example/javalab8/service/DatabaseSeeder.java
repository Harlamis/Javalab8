package org.example.javalab8.service;
import lombok.RequiredArgsConstructor;
import org.example.javalab8.model.*;
import org.example.javalab8.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            seedInstructorsAndCourses();
            seedStudents();
            System.out.println("--- Database Seed Completed Successfully ---");
        }
    }

    private void seedInstructorsAndCourses() {
        for (int i = 1; i <= 5; i++) {
            InstructorDetail detail = new InstructorDetail();
            detail.setBio("Experienced developer with " + (i * 3) + " years in the industry.");
            detail.setSocialMedia("@instructor_link_" + i);

            Instructor instructor = new Instructor();
            instructor.setName("Instructor " + i);
            instructor.setSpeciality("Technology " + i);
            instructor.setExperience((double) (i * 2));
            instructor.setInstructorDetail(detail);

            instructorRepository.save(instructor);

            for (int j = 1; j <= 3; j++) {
                Course course = new Course();
                course.setName("Course " + i + "." + j);
                course.setDescription("Description for course " + j);
                course.setPrice(100.0 * j);
                course.setInstructor(instructor); // ManyToOne
                courseRepository.save(course);
            }
        }
    }

    private void seedStudents() {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            Student student = new Student();
            student.setName("Student Name " + i);
            student.setEmail("student" + i + "@university.edu");
            student.setRegistrationDate(LocalDateTime.now().minusDays(i));
            students.add(student);
        }
        studentRepository.saveAll(students);
    }
}
