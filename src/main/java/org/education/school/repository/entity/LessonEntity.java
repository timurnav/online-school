package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lessons")
@NamedQueries({
        @NamedQuery(name = LessonEntity.GET_ALL, query = "SELECT l FROM LessonEntity l"),
        @NamedQuery(name = LessonEntity.DELETE, query = "DELETE FROM LessonEntity l WHERE l.id=:id")
})
public class LessonEntity extends LearningItemEntity {

    public static final String GET_ALL = "Lessons.getAll";
    public static final String DELETE = "Lessons.delete";

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(mappedBy = "lesson")
    private List<HomeWorkEntity> homeWorks;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public List<HomeWorkEntity> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWorkEntity> homeWorks) {
        this.homeWorks = homeWorks;
    }
}
