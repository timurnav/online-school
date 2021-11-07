package org.education.school.repository.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@NamedQueries({
        @NamedQuery(name = CourseEntity.GET_ALL, query = "SELECT c FROM CourseEntity c"),
        @NamedQuery(name = CourseEntity.GET_ALL_WITH_USERS, query = "SELECT c FROM CourseEntity c LEFT JOIN FETCH c.teacher LEFT JOIN FETCH c.students"),
        @NamedQuery(name = CourseEntity.GET_ALL_WITH_LESSONS, query = "SELECT c FROM CourseEntity c LEFT JOIN FETCH c.teacher LEFT JOIN FETCH c.lessons"),
        @NamedQuery(name = CourseEntity.DELETE, query = "DELETE FROM CourseEntity c WHERE c.id=:id")
})
public class CourseEntity extends LearningItemEntity {

    public static final String GET_ALL = "Courses.getAll";
    public static final String GET_ALL_WITH_USERS = "Courses.getAllWithTeachersAndStudents";
    public static final String GET_ALL_WITH_LESSONS = "Courses.getAllWithLessons";
    public static final String DELETE = "Courses.delete";

    @ManyToMany(mappedBy = "learningCourses")
    private List<StudentEntity> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @OneToMany(mappedBy = "course")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<LessonEntity> lessons;
    private String level;

    public List<StudentEntity> getStudents() {
        if (this.students == null) {
            students = new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
