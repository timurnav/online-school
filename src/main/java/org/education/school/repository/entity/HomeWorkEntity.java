package org.education.school.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "home_works")
@NamedQueries({
        @NamedQuery(name = HomeWorkEntity.GET_ALL, query = "SELECT hw FROM HomeWorkEntity hw"),
        @NamedQuery(name = HomeWorkEntity.DELETE, query = "DELETE FROM HomeWorkEntity hw WHERE hw.id=:id")
})
public class HomeWorkEntity extends LearningItemEntity {

    public static final String GET_ALL = "HomeWorks.getAll";
    public static final String DELETE = "HomeWorks.delete";

    private Date till;
    private int attempts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    public Date getTill() {
        return till;
    }

    public void setTill(Date till) {
        this.till = till;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }
}
