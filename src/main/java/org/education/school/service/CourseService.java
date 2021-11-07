package org.education.school.service;

import org.education.school.repository.CourseRepository;
import org.education.school.repository.entity.CourseEntity;
import org.education.school.repository.entity.GlobalSeqIdEntity;
import org.education.school.repository.entity.LessonEntity;
import org.education.school.service.dto.Course;
import org.education.school.service.dto.CourseStudent;
import org.education.school.service.dto.Teacher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAll() {
        List<CourseEntity> allWithLessons = repository.getAllWithLessons();
        Map<Integer, List<LessonEntity>> lessons = allWithLessons.stream()
                .collect(Collectors.toMap(GlobalSeqIdEntity::getId, CourseEntity::getLessons));
        List<CourseEntity> entities = repository.getAllWithUsers();
        return entities.stream()
                .map(entity -> {
                    Date startDate = lessons.getOrDefault(entity.getId(), Collections.emptyList()).stream()
                            .map(LessonEntity::getDate)
                            .sorted()
                            .findFirst()
                            .orElse(null);
                    List<CourseStudent> students = entity.getStudents().stream()
                            .map(CourseStudent::of)
                            .collect(Collectors.toList());
                    return new Course(
                            entity.getId(),
                            entity.getTitle(),
                            entity.getDescription(),
                            entity.getLevel(),
                            startDate,
                            Teacher.of(entity.getTeacher()),
                            students);
                })
                .collect(Collectors.toList());
    }
}
