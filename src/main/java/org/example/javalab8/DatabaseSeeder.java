package org.example.javalab8;

import org.example.javalab8.model.*;
import org.example.javalab8.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final ReviewRepository reviewRepository;

    public DatabaseSeeder(StudentRepository studentRepository,
                          InstructorRepository instructorRepository,
                          CourseRepository courseRepository,
                          LessonRepository lessonRepository,
                          ReviewRepository reviewRepository) {
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (instructorRepository.count() > 0) {
            System.out.println("DB is seeded.");
            return;
        }

        System.out.println("Seeding...");

        List<Instructor> instructors = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Instructor instructor = new Instructor();
            instructor.setName("Instructor Name " + i);
            instructor.setExperience(Math.round((Math.random() * 10) * 10.0) / 10.0);
            instructor.setSpeciality("Speciality " + (i % 5 + 1));
            instructors.add(instructor);
        }
        instructorRepository.saveAll(instructors);

        // 2. Створюємо 50 Студентів
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Student student = new Student();
            student.setName("Student Name " + i);
            student.setEmail("student" + i + "@example.org");
            student.setRegistrationDate(LocalDateTime.now().minusDays(i));
            students.add(student);
        }
        studentRepository.saveAll(students);

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Course course = new Course();
            course.setName("Course Title " + (i + 1));
            course.setDescription("Detailed description for course " + (i + 1));
            course.setLength((i % 5 + 1) * 10);
            course.setPrice(100.0 + (i * 5));

            course.setInstructor(instructors.get(i));

            course.setStudents(List.of(students.get(i)));

            courses.add(course);
        }
        courseRepository.saveAll(courses);

        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Lesson lesson = new Lesson();
            lesson.setTitle("Lesson Title " + (i + 1));
            lesson.setContent("Content for lesson " + (i + 1) + ". Here we learn important things.");

            lesson.setCourse(courses.get(i));
            lesson.setInstructor(instructors.get(i)); // Зв'язок OneToOne

            lessons.add(lesson);
        }
        lessonRepository.saveAll(lessons);

        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Review review = new Review();
            review.setRating(Math.round((Math.random() * 4 + 1) * 10.0) / 10.0); // Рейтинг від 1.0 до 5.0
            review.setComment("This is review comment number " + (i + 1));

            review.setAuthor(students.get(i));
            review.setCourse(courses.get(i));

            reviews.add(review);
        }
        reviewRepository.saveAll(reviews);

        System.out.println("DB seeded successfully!");
    }
}