package org.education.school.repository.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "courses")
@NamedQueries({
        @NamedQuery(name = CourseEntity.GET_ALL, query = "SELECT c FROM CourseEntity c")
})
public class CourseEntity extends LearningItemEntity {

    public static final String GET_ALL = "Courses.getAll";

//    private List<LessonEntity> lessons;
//
//    public List<LessonEntity> getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(List<LessonEntity> lessons) {
//        this.lessons = lessons;
//    }
}
