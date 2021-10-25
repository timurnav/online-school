package org.education.school.repository.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "home_works")
@NamedQueries({
        @NamedQuery(name = HomeWorkEntity.GET_ALL, query = "SELECT hw FROM HomeWorkEntity hw")
})
public class HomeWorkEntity extends LearningItemEntity {

    public static final String GET_ALL = "HomeWorks.getAll";

    private Date till;
    private int attempts;

    public Date getTill() {
        return till;
    }

    public void setTill(Date till) {
        this.till = till;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
