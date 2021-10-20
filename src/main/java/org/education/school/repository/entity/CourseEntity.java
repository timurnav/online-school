package org.education.school.repository.entity;

import java.util.List;

public class CourseEntity {

    private Integer id;
    private String title;
    private String description;
    private List<LessonEntity> lessons;
}
