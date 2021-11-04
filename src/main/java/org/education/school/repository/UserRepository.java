package org.education.school.repository;

import org.education.school.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public UserEntity save(UserEntity entity) {
        return em.merge(entity);
    }

    public UserEntity get(int id) {
        return em.find(UserEntity.class, id);
    }

    public List<UserEntity> getAll() {
        return em.createNamedQuery(UserEntity.GET_ALL, UserEntity.class)
                .getResultList();
    }
}
