package org.education.school.service.dto;

import java.util.List;

public class LmsCourse {
    public final int id;
    public final String title;
    public final String description;
    public final List<LmsCourseLesson> lessons;

    public LmsCourse(int id, String title, String description, List<LmsCourseLesson> lessons) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lessons = lessons;
    }
}
