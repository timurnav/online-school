package org.education.school.repository;

import org.education.school.repository.entity.AdminEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdminRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public AdminEntity save(AdminEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public AdminEntity get(int id) {
        return em.find(AdminEntity.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(AdminEntity.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public List<AdminEntity> getAll() {
        return em.createNamedQuery(AdminEntity.GET_ALL, AdminEntity.class)
                .getResultList();
    }
}
