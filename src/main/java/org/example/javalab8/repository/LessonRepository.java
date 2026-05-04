package org.example.javalab8.repository;

import org.example.javalab8.model.Lesson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LessonRepository {
    private int currentId = 1;
    private final List<Lesson> lessons = new ArrayList<>();

    public List<Lesson> findAll() {
        return lessons;
    }

    public Optional<Lesson> findById(int id) {
        return lessons.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    public void save(Lesson lesson) {
        if (lesson.getId() == null) {
            lesson.setId(currentId);
            lessons.add(lesson);
            currentId++;
            return;
        }
        Optional<Lesson> match = findById(lesson.getId());
        if (match.isPresent()) {
            int index = lessons.indexOf(match.get());
            lessons.set(index, lesson);
        }
    }

    public Optional<Lesson> deleteById(int id) {
        Optional<Lesson> match = findById(id);
        if (match.isPresent()) {
            lessons.remove(match.get());
            return match;
        }
        return Optional.empty();
    }
}
