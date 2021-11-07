package org.education.school.service.dto;

public class User {

    public final Integer id;
    public final String fullName;
    public final String email;
    public final String password;
    public final boolean banned;
    public final UserType type;

    public User(Integer id, String fullName, String email, String password, boolean banned, UserType type) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.banned = banned;
        this.type = type;
    }
}
