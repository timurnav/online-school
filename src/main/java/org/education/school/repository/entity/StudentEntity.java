package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = StudentEntity.DELETE, query = "DELETE FROM StudentEntity s WHERE s.id=:id"),
        @NamedQuery(name = StudentEntity.GET_ALL, query = "SELECT s FROM StudentEntity s")
})
public class StudentEntity {

    public static final int START_SEQ = 10000;

    public static final String DELETE = "Students.delete";
    public static final String GET_ALL = "Students.getAll";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false, name = "surname")
    private String lastName;
//    @Column(columnDefinition = "timestamp default now() not null") // not working :/
    @Column(nullable = false)
    private Date registered = new Date();
    @Embedded
    private UserContactsEntity contacts = new UserContactsEntity();
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
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
