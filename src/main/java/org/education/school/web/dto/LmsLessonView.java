package org.education.school.web.dto;

import java.util.List;

public class LmsLessonView {
    private int id;
    private String title;
    private String description;
    private List<LmsHomeworkView> homeWorks;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setHomeWorks(List<LmsHomeworkView> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public List<LmsHomeworkView> getHomeWorks() {
        return homeWorks;
    }
}
