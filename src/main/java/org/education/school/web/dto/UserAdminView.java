package org.education.school.web.dto;

import org.education.school.service.dto.User;
import org.education.school.service.dto.UserType;

import java.util.Set;

public class UserAdminView {

    private Integer id;
    private String name;
    private String type;
    private String email;
    private boolean banned;
    private Set<Integer> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Set<Integer> getCourses() {
        return courses;
    }

    public void setCourses(Set<Integer> courses) {
        this.courses = courses;
    }

    public static UserAdminView of(User user) {
        UserAdminView view = new UserAdminView();
        view.id = user.id;
        view.name = user.fullName;
        view.type = user.type.name();
        view.email = user.email;
        view.banned = user.banned;
        return view;
    }

    public User toDto() {
        return new User(id, name, email, null, banned, UserType.valueOf(type));
    }
}
