package org.education.school.repository;

import org.education.school.repository.entity.CourseEntity;
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
}