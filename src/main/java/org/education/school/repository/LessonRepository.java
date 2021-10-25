package org.education.school.repository;

import org.education.school.repository.entity.LessonEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LessonRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public LessonEntity save(LessonEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public LessonEntity get(int id) {
        return em.find(LessonEntity.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(LessonEntity.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public List<LessonEntity> getAll() {
        return em.createNamedQuery(LessonEntity.GET_ALL, LessonEntity.class)
                .getResultList();
    }
}
