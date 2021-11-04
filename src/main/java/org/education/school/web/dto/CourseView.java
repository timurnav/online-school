package org.education.school.web.dto;

public class CourseView {

    private String title;
    private String level;
    private String description;

    public CourseView() {
    }

    public CourseView(String title, String level, String description) {
        this.title = title;
        this.level = level;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
