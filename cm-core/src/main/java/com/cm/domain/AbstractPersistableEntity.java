package com.cm.domain;

import javax.persistence.*;

@MappedSuperclass
public class AbstractPersistableEntity implements JPAEntity<Long> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cm_sequence")
    @SequenceGenerator(name = "cm_sequence", sequenceName = "CM_T_SEQUENCE")
    private long id;

    public long getId() {
        return id;
    }
}
