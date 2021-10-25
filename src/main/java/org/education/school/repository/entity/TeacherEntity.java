package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "teachers")
@DiscriminatorValue("teacher")
@NamedQueries({
        @NamedQuery(name = TeacherEntity.GET_ALL, query = "SELECT t FROM TeacherEntity t")
})
public class TeacherEntity extends UserEntity {

    public static final String GET_ALL = "Teachers.getAll";

    //    private List<CourseEntity> teachingCourses;
//    private TeacherEntity supervisor;

    //    public List<CourseEntity> getTeachingCourses() {
//        return teachingCourses;
//    }
//
//    public void setTeachingCourses(List<CourseEntity> teachingCourses) {
//        this.teachingCourses = teachingCourses;
//    }
//
//    public TeacherEntity getSupervisor() {
//        return supervisor;
//    }
//
//    public void setSupervisor(TeacherEntity supervisor) {
//        this.supervisor = supervisor;
//    }
}
