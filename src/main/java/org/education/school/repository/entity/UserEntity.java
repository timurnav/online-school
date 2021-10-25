package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "user_type")
@Inheritance
@NamedQueries(
        @NamedQuery(name = UserEntity.DELETE, query = "DELETE FROM StudentEntity s WHERE s.id=:id")
)
public class UserEntity {

    public static final String DELETE = "Users.delete";

    public static final int START_SEQ = 10000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;
    private String firstName;
    private String lastName;
    private Date registered;
    @Embedded
    private UserContactsEntity contacts;

    @PostLoad
    public void postLoad() {
        if (contacts == null) {
            contacts = new UserContactsEntity();
        }
    }

    @PrePersist
    public void prePersist() {
        if (registered == null) {
            registered = new Date();
        }
    }

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
}
