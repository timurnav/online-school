package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "user_type")
@Inheritance
public class UserEntity extends GlobalSeqIdEntity {

    @Embedded
    private FullName fullName;
    @Column(updatable = false)
    private Date registered;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
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

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
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
