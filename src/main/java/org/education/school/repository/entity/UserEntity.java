package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "user_type")
@Inheritance
public class UserEntity extends GlobalSeqIdEntity {

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
