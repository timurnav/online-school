package org.education.school.service.dto;

import org.education.school.repository.entity.StudentEntity;

public class CourseStudent {

    public final int id;
    public final String name;

    public CourseStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CourseStudent of(StudentEntity entity) {
        return new CourseStudent(entity.getId(), entity.getFullName().toString());
    }
}
