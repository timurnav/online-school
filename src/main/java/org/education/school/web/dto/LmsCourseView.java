package org.education.school.web.dto;

import java.util.List;

public class LmsCourseView {

    private int id;
    private String title;
    private String description;
    private List<LmsCourseLessonView> lessons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LmsCourseLessonView> getLessons() {
        return lessons;
    }

    public void setLessons(List<LmsCourseLessonView> lessons) {
        this.lessons = lessons;
    }
}
