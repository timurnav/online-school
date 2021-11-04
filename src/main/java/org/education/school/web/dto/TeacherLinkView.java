package org.education.school.web.dto;

public class TeacherLinkView {

    private int id;
    private String name;

    public TeacherLinkView() {
    }

    public TeacherLinkView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
