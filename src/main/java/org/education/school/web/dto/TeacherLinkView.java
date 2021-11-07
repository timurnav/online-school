package org.education.school.web.dto;

import org.education.school.service.dto.Teacher;

public class TeacherLinkView {

    private int id;
    private String name;

    public static TeacherLinkView of(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        TeacherLinkView view = new TeacherLinkView();
        view.id = teacher.id;
        view.name = teacher.fullName;
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
}
