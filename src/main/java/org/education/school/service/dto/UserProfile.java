package org.education.school.service.dto;

public class UserProfile {
    public final int id;
    public final String name;
    public final String title;
    public final String img;

    public UserProfile(int id, String name, String title, String img) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.img = img;
    }
}
