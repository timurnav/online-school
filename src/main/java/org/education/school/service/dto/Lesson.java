package org.education.school.service.dto;

import java.util.List;

public class Lesson {
    public final int id;
    public final String title;
    public final String description;
    public final List<HomeWork> homeworks;

    public Lesson(int id, String title, String description, List<HomeWork> homeworks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.homeworks = homeworks;
    }
}
