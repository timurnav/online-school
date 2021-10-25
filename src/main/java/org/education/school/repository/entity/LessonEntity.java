package org.education.school.repository.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
@NamedQueries({
        @NamedQuery(name = LessonEntity.GET_ALL, query = "SELECT l FROM LessonEntity l")
})
public class LessonEntity extends LearningItemEntity {

    public static final String GET_ALL = "Lessons.getAll";

//    private List<HomeWorkEntity> homeWorks;
//
//    public List<HomeWorkEntity> getHomeWorks() {
//        return homeWorks;
//    }
//
//    public void setHomeWorks(List<HomeWorkEntity> homeWorks) {
//        this.homeWorks = homeWorks;
//    }
}
