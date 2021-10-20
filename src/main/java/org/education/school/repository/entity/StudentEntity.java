package org.education.school.repository.entity;

import java.util.List;

public class StudentEntity {

    private Integer id;
    private String firstName;
    private String lastName;
    private UserContactsEntity contacts;
    private List<CourseEntity> learningCourses;

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

    public List<CourseEntity> getLearningCourses() {
        return learningCourses;
    }

    public void setLearningCourses(List<CourseEntity> learningCourses) {
        this.learningCourses = learningCourses;
    }
}
