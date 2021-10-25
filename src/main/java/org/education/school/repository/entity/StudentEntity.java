package org.education.school.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = StudentEntity.DELETE, query = "DELETE FROM StudentEntity s WHERE s.id=:id"),
        @NamedQuery(name = StudentEntity.GET_ALL, query = "SELECT s FROM StudentEntity s")
})
public class StudentEntity {

    public static final String DELETE = "Students.delete";
    public static final String GET_ALL = "Students.getAll";

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    @Embedded
    private UserContactsEntity contacts;
//    private List<CourseEntity> learningCourses;

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

//    public List<CourseEntity> getLearningCourses() {
//        return learningCourses;
//    }
//
//    public void setLearningCourses(List<CourseEntity> learningCourses) {
//        this.learningCourses = learningCourses;
//    }
}
