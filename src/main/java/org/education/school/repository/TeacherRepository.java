package org.education.school.repository;

import org.education.school.repository.entity.TeacherEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeacherRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public TeacherEntity save(TeacherEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public TeacherEntity get(int id) {
        return em.find(TeacherEntity.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(TeacherEntity.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public List<TeacherEntity> getAll() {
        return em.createNamedQuery(TeacherEntity.GET_ALL, TeacherEntity.class)
                .getResultList();
    }
}
