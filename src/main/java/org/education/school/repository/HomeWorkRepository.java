package org.education.school.repository;

import org.education.school.repository.entity.HomeWorkEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HomeWorkRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public HomeWorkEntity save(HomeWorkEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public HomeWorkEntity get(int id) {
        return em.find(HomeWorkEntity.class, id);
    }

    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(HomeWorkEntity.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    public List<HomeWorkEntity> getAll() {
        return em.createNamedQuery(HomeWorkEntity.GET_ALL, HomeWorkEntity.class)
                .getResultList();
    }
}
