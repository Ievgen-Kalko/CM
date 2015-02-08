package com.cm.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractAuditableEntity extends AbstractPersistableEntity {

    @Column(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "rowstatus")
    @Enumerated(EnumType.ORDINAL)
    private RowStatus rowstatus;

    public AbstractAuditableEntity() {
    }

    public RowStatus getRowstatus() {
        return rowstatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setRowstatus(RowStatus rowstatus) {
        this.rowstatus = rowstatus;
    }

    @PrePersist
    public void beforeSave() {
        setCreatedAt(new Date());
        setRowstatus(RowStatus.ACTIVE);
    }

    public static enum RowStatus {
        REMOVED(0L),
        ACTIVE(1L),
        ARCHIVED(2L);

        private final long value;

        private RowStatus(final long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }
    }
}
