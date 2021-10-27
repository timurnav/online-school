package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@NamedQueries({
        @NamedQuery(name = CourseEntity.GET_ALL, query = "SELECT c FROM CourseEntity c"),
        @NamedQuery(name = CourseEntity.DELETE, query = "DELETE FROM CourseEntity c WHERE c.id=:id")
})
public class CourseEntity extends LearningItemEntity {

    public static final String GET_ALL = "Courses.getAll";
    public static final String DELETE = "Courses.delete";

    @ManyToMany(mappedBy = "learningCourses")
    private List<StudentEntity> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @OneToMany(mappedBy = "course")
    private List<LessonEntity> lessons;

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }
}
