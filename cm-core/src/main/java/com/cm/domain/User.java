package com.cm.domain;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Ievgen on 2/4/2015.
 */
@Entity
@Table(name = "CM_T_USERS", schema = "CM", catalog = "")
public class User {
    private long id;
    private String type;
    private String email;
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
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        User cmTUsers = (User) o;

        if (createdat != cmTUsers.createdat) return false;
        if (id != cmTUsers.id) return false;
        if (email != null ? !email.equals(cmTUsers.email) : cmTUsers.email != null) return false;
        if (rowstatus != null ? !rowstatus.equals(cmTUsers.rowstatus) : cmTUsers.rowstatus != null) return false;
        if (type != null ? !type.equals(cmTUsers.type) : cmTUsers.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (createdat ^ (createdat >>> 32));
        result = 31 * result + (rowstatus != null ? rowstatus.hashCode() : 0);
        return result;
    }
}
