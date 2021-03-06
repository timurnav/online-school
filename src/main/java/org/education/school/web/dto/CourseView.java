package org.education.school.web.dto;

import org.education.school.service.dto.Course;

import java.util.Date;

public class CourseView {

    private int id;
    private String title;
    private String level;
    private String description;
    private Date startDate;
    private TeacherLinkView teacher;

    public CourseView() {
    }

    public CourseView(int id, String title, String level, String description, Date startDate, TeacherLinkView teacher) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.description = description;
        this.startDate = startDate;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TeacherLinkView getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherLinkView teacher) {
        this.teacher = teacher;
    }

    public static CourseView of(Course course) {
        TeacherLinkView teacherLinkView = TeacherLinkView.of(course.teacher);
        return new CourseView(
                course.id, course.title, course.level, course.description, course.startDate, teacherLinkView
        );
    }
}
