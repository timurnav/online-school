package org.education.school.service;

import org.education.school.repository.CourseRepository;
import org.education.school.repository.StudentRepository;
import org.education.school.repository.entity.*;
import org.education.school.service.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> getAll() {
        List<CourseEntity> allWithLessons = courseRepository.getAllWithLessons();
        Map<Integer, List<LessonEntity>> lessons = allWithLessons.stream()
                .collect(Collectors.toMap(GlobalSeqIdEntity::getId, CourseEntity::getLessons));
        List<CourseEntity> entities = courseRepository.getAllWithUsers();
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

    public List<LmsCourse> getByTeacher(int teacherId) {
        List<CourseEntity> courseEntities = courseRepository.getByTeacherWithLessons(teacherId);
        return toDto(courseEntities);
    }

    public List<LmsCourse> getByStudent(int studentId) {
        List<CourseEntity> courseEntities = studentRepository.getStudentCourses(studentId);
        return toDto(courseEntities);
    }

    private List<LmsCourse> toDto(List<CourseEntity> courseEntities) {
        List<LmsCourse> courses = new ArrayList<>();
        courseEntities.forEach(e -> {
            List<LmsCourseLesson> lessons = e.getLessons().stream()
                    .map(lessonEntity -> new LmsCourseLesson(lessonEntity.getId(), lessonEntity.getTitle(), lessonEntity.getDescription()))
                    .collect(Collectors.toList());
            courses.add(new LmsCourse(e.getId(), e.getTitle(), e.getDescription(), lessons));
        });
        return courses;
    }

    public Lesson getLesson(Integer lessonId) {
        LessonEntity entity = courseRepository.getLessonWithHomeWorks(lessonId);
        List<HomeWork> homeworks = entity.getHomeWorks().stream()
                .map(hw -> new HomeWork(hw.getId(), hw.getTitle(), hw.getDescription(), hw.getAttempts(), hw.getTill()))
                .collect(Collectors.toList());
        return new Lesson(entity.getId(), entity.getTitle(), entity.getDescription(), homeworks);
    }
}
