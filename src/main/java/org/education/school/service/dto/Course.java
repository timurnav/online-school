package org.education.school.service.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Course {
    public final int id;
    public final String title;
    public final String description;
    public final String level;
    public final Date startDate;
    public final Teacher teacher;
    public final List<CourseStudent> students;
    public final Set<Integer> studentIds;

    public Course(int id, String title, String description, String level, Date startDate,
                  Teacher teacher, List<CourseStudent> students) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.level = level;
        this.startDate = startDate;
        this.teacher = teacher;
        this.students = students;
        this.studentIds = students.stream()
                .map(a -> a.id)
                .collect(Collectors.toSet());
    }

    public boolean hasStudent(int id) {
        return studentIds.contains(id);
    }
}
