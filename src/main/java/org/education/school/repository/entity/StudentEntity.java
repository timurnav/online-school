package org.education.school.repository.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
//@Table(name = "students")
@DiscriminatorValue("student")
@NamedQueries({
        @NamedQuery(name = StudentEntity.GET_ALL, query = "SELECT s FROM StudentEntity s")
})
public class StudentEntity extends UserEntity {

    public static final String GET_ALL = "Students.getAll";

//    private List<CourseEntity> learningCourses;

//    public List<CourseEntity> getLearningCourses() {
//        return learningCourses;
//    }
//
//    public void setLearningCourses(List<CourseEntity> learningCourses) {
//        this.learningCourses = learningCourses;
//    }
}
