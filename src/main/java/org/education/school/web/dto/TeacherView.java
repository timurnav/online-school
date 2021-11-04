package org.education.school.web.dto;

import java.util.List;

public class TeacherView {

    private int id;
    private String name;
    private String img;
    private String title;
    private List<String> courses;

    public TeacherView() {
    }

    public TeacherView(int id, String name, String img, String title, List<String> courses) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.title = title;
        this.courses = courses;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
