package org.education.school.repository;

import org.education.school.repository.entity.CourseEntity;
import org.education.school.repository.entity.LessonEntity;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public CourseEntity save(CourseEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public CourseEntity get(int id) {
        return em.find(CourseEntity.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(CourseEntity.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public List<CourseEntity> getAll() {
        return em.createNamedQuery(CourseEntity.GET_ALL, CourseEntity.class)
                .getResultList();
    }

    public List<CourseEntity> getAllWithLessons() {
        return em.createNamedQuery(CourseEntity.GET_ALL_WITH_LESSONS, CourseEntity.class)
                .getResultList();
    }

    public List<CourseEntity> getAllWithUsers() {
        return em.createNamedQuery(CourseEntity.GET_ALL_WITH_USERS, CourseEntity.class)
                .getResultList();
    }

    public List<CourseEntity> getByTeacherWithLessons(int teacherId) {
        return em.createNamedQuery(CourseEntity.GET_BY_TEACHER_WITH_LESSONS, CourseEntity.class)
                .setParameter("id", teacherId)
                .getResultList();
    }

    public LessonEntity getLessonWithHomeWorks(int lessonId) {
        List<LessonEntity> lessons = em.createNamedQuery(CourseEntity.GET_LESSON_WITH_HW, LessonEntity.class)
                .setParameter("id", lessonId)
                .getResultList();
        return DataAccessUtils.singleResult(lessons);
    }
}
