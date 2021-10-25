package org.education.school.repository.entity;

import javax.persistence.*;

@MappedSuperclass
@NamedQueries(
        @NamedQuery(name = GlobalSeqIdEntity.DELETE, query = "DELETE FROM StudentEntity s WHERE s.id=:id")
)
public class GlobalSeqIdEntity {

    public static final String DELETE = "Users.delete";

    public static final int START_SEQ = 10000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
