package org.education.school.repository.entity;

import org.education.school.service.dto.UserType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("student")
@NamedQueries({
        @NamedQuery(name = StudentEntity.GET_ALL, query = "SELECT s FROM StudentEntity s"),
        @NamedQuery(name = StudentEntity.GET_ALL_WITH_CONT, query = "SELECT s FROM StudentEntity s LEFT JOIN FETCH s.contacts"),
        @NamedQuery(name = StudentEntity.GET_COURSES_WITH_LESSONS, query = "SELECT DISTINCT c FROM StudentEntity s LEFT JOIN s.learningCourses c LEFT JOIN FETCH c.lessons LEFT JOIN FETCH c.teacher"),
        @NamedQuery(name = StudentEntity.DELETE, query = "DELETE FROM StudentEntity s WHERE s.id=:id")
})
public class StudentEntity extends UserEntity {

    public static final String GET_ALL = "Students.getAll";
    public static final String GET_ALL_WITH_CONT = "Students.getAllWithCont";
    public static final String DELETE = "Students.delete";
    public static final String GET_COURSES_WITH_LESSONS = "Students.getOneWithCourses";

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<CourseEntity> learningCourses;

    @ElementCollection
    @CollectionTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "course_id")
    public Set<Integer> learningCourseIds;

    public List<CourseEntity> getLearningCourses() {
        if (learningCourses == null) {
            learningCourses = new ArrayList<>();
        }
        return learningCourses;
    }

    public void setLearningCourses(List<CourseEntity> learningCourses) {
        this.learningCourses = learningCourses;
    }

    public Set<Integer> getLearningCourseIds() {
        if (learningCourseIds == null) {
            learningCourseIds = new HashSet<>();
        }
        return learningCourseIds;
    }

    public void setLearningCourseIds(Set<Integer> learningCourseIds) {
        this.learningCourseIds = learningCourseIds;
    }

    @Override
    public UserType type() {
        return UserType.STUDENT;
    }
}
