package org.education.school.service.dto;

public class LmsCourseLesson {
    public final int id;
    public final String title;
    public final String description;

    public LmsCourseLesson(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
