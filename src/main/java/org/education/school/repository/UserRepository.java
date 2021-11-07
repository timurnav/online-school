package org.education.school.repository;

import org.education.school.repository.entity.UserCredentialsEntity;
import org.education.school.repository.entity.UserEntity;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.education.school.repository.entity.UserCredentialsEntity.BY_EMAIL;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public UserEntity save(UserEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public UserEntity get(int id) {
        return em.find(UserEntity.class, id);
    }

    public List<UserEntity> getAll() {
        return em.createNamedQuery(UserEntity.GET_ALL, UserEntity.class)
                .getResultList();
    }

    public UserCredentialsEntity findCredentials(String email) {
        List<UserCredentialsEntity> users = em.createNamedQuery(BY_EMAIL, UserCredentialsEntity.class)
                .setParameter(1, email)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }
}
