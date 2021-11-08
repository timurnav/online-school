package org.education.school.web.dto;

import org.education.school.service.dto.UserProfile;

public class ProfileView {
    private int id;
    private String name;
    private String title;
    private String img;

    public static ProfileView of(UserProfile profile) {
        ProfileView view = new ProfileView();
        view.id = profile.id;
        view.name = profile.name;
        view.title = profile.title;
        view.img = profile.img;
        return view;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
