package org.education.school.repository.entity;

import org.education.school.service.dto.UserType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("admin")
@NamedQueries({
        @NamedQuery(name = AdminEntity.GET_ALL, query = "SELECT s FROM AdminEntity s"),
        @NamedQuery(name = AdminEntity.GET_ALL_WITH_CONT, query = "SELECT s FROM AdminEntity s LEFT JOIN FETCH s.contacts"),
        @NamedQuery(name = AdminEntity.DELETE, query = "DELETE FROM AdminEntity s WHERE s.id=:id")
})
public class AdminEntity extends UserEntity {

    public static final String GET_ALL = "Admins.getAll";
    public static final String GET_ALL_WITH_CONT = "Admins.getAllWithCont";
    public static final String DELETE = "Admins.delete";

    @Override
    public UserType type() {
        return UserType.ADMIN;
    }
}
