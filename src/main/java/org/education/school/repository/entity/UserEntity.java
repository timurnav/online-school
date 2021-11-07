package org.education.school.repository.entity;

import org.education.school.service.dto.UserType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "user_type")
@Inheritance
@NamedQueries(
        @NamedQuery(name = UserEntity.GET_ALL, query = "SELECT u FROM UserEntity u LEFT JOIN FETCH u.contacts")
)
public abstract class UserEntity extends GlobalSeqIdEntity {

    public static final String GET_ALL = "Users.getAll";
    @Embedded
    private FullName fullName;
    private String password;
    @Column(updatable = false)
    private Date registered;
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private UserContactsEntity contacts = new UserContactsEntity(this);
    private boolean banned;
    private String image;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection
    private Set<UserRole> roles;
    @Version
    private int version;

    @PostLoad
    public void postLoad() {
        if (contacts == null) {
            this.contacts = new UserContactsEntity(this);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (this.contacts != null) {
            this.contacts.setOwner(this);
        }
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Transient
    public abstract UserType type();
}
