package org.education.school.repository.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Set;

import static org.education.school.repository.entity.UserCredentialsEntity.BY_EMAIL;

@Entity
@Table(name = "users")
@NamedQuery(name = BY_EMAIL, query = "SELECT DISTINCT c FROM UserCredentialsEntity c WHERE c.email=?1")
@Immutable
@SecondaryTable(name = "contacts", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id"))
public class UserCredentialsEntity extends GlobalSeqIdEntity {

    public static final String BY_EMAIL = "User.byEmail";

    @Column(table = "contacts", name = "email")
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;
    private boolean banned;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean enabled) {
        this.banned = enabled;
    }
}
