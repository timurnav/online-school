package org.education.school.web.dto;

import org.education.school.service.dto.User;

public class UserAdminView {

    private int id;
    private String name;
    private String type;
    private String email;
    private boolean banned;

    public UserAdminView() {
    }

    public UserAdminView(int id, String name, String type, String email, boolean banned) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.banned = banned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public static UserAdminView of(User user) {
        return new UserAdminView(user.id, user.fullName, user.type.name(),
                user.email, user.banned);
    }
}
