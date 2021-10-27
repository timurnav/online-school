package org.education.school.repository;

import org.education.school.repository.entity.StudentEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public StudentEntity save(StudentEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public StudentEntity get(int id) {
        return em.find(StudentEntity.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(StudentEntity.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public List<StudentEntity> getAll() {
        return em.createNamedQuery(StudentEntity.GET_ALL, StudentEntity.class)
                .getResultList();
    }

    public List<StudentEntity> getAllWithCont() {
        return em.createNamedQuery(StudentEntity.GET_ALL_WITH_CONT, StudentEntity.class)
                .getResultList();
    }
}
