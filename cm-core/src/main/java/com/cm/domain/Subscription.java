package com.cm.domain;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Ievgen on 2/4/2015.
 */
@Entity
@Table(name = "CM_T_SUBSCRIPTS", schema = "CM", catalog = "")
public class Subscription {
    private long id;
    private long createdat;
    private BigInteger rowstatus;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CREATEDAT")
    public long getCreatedat() {
        return createdat;
    }

    public void setCreatedat(long createdat) {
        this.createdat = createdat;
    }

    @Basic
    @Column(name = "ROWSTATUS")
    public BigInteger getRowstatus() {
        return rowstatus;
    }

    public void setRowstatus(BigInteger rowstatus) {
        this.rowstatus = rowstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (createdat != that.createdat) return false;
        if (id != that.id) return false;
        if (rowstatus != null ? !rowstatus.equals(that.rowstatus) : that.rowstatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (createdat ^ (createdat >>> 32));
        result = 31 * result + (rowstatus != null ? rowstatus.hashCode() : 0);
        return result;
    }
}
