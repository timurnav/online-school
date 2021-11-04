package org.education.school.web.dto;

import java.util.Date;

public class CourseView {

    private String title;
    private String level;
    private String description;
    private Date startDate;

    public CourseView() {
    }

    public CourseView(String title, String level, String description, Date startDate) {
        this.title = title;
        this.level = level;
        this.description = description;
        this.startDate = startDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
