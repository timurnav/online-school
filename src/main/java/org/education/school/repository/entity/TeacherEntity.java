package org.education.school.repository.entity;

import java.util.List;

public class TeacherEntity {

    private Integer id;
    private String firstName;
    private String lastName;
    private UserContactsEntity contacts;
    private List<CourseEntity> teachingCourses;
    private TeacherEntity supervisor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserContactsEntity getContacts() {
        return contacts;
    }

    public void setContacts(UserContactsEntity contacts) {
        this.contacts = contacts;
    }

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
}
