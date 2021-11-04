package org.education.school.repository.entity;

import org.education.school.service.dto.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("student")
@NamedQueries({
        @NamedQuery(name = StudentEntity.GET_ALL, query = "SELECT s FROM StudentEntity s"),
        @NamedQuery(name = StudentEntity.GET_ALL_WITH_CONT, query = "SELECT s FROM StudentEntity s LEFT JOIN FETCH s.contacts"),
        @NamedQuery(name = StudentEntity.DELETE, query = "DELETE FROM StudentEntity s WHERE s.id=:id")
})
public class StudentEntity extends UserEntity {

    public static final String GET_ALL = "Students.getAll";
    public static final String GET_ALL_WITH_CONT = "Students.getAllWithCont";
    public static final String DELETE = "Students.delete";

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<CourseEntity> learningCourses;

    public List<CourseEntity> getLearningCourses() {
        return learningCourses;
    }

    public void setLearningCourses(List<CourseEntity> learningCourses) {
        this.learningCourses = learningCourses;
    }

    @Override
    public UserType type() {
        return UserType.STUDENT;
    }
}
