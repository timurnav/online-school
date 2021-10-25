package org.education.school.repository.entity;

import java.util.List;

public class LessonEntity {

    private Integer id;
    private String title;
    private String description;
    private List<HomeWorkEntity> homeWorks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<HomeWorkEntity> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWorkEntity> homeWorks) {
        this.homeWorks = homeWorks;
    }
}
