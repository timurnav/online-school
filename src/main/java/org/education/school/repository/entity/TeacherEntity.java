package org.education.school.repository.entity;

import org.education.school.service.dto.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("teacher")
@NamedQueries({
        @NamedQuery(name = TeacherEntity.GET_ALL, query = "SELECT t FROM TeacherEntity t"),
        @NamedQuery(name = TeacherEntity.DELETE, query = "DELETE FROM TeacherEntity t WHERE t.id=:id")
})
public class TeacherEntity extends UserEntity {

    public static final String GET_ALL = "Teachers.getAll";
    public static final String DELETE = "Teachers.delete";

    @OneToMany(mappedBy = "teacher")
    private List<CourseEntity> teachingCourses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private TeacherEntity supervisor;

    public List<CourseEntity> getTeachingCourses() {
        return teachingCourses;
    }

    public void setTeachingCourses(List<CourseEntity> teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    public TeacherEntity getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(TeacherEntity supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public UserType type() {
        return UserType.TEACHER;
    }
}
