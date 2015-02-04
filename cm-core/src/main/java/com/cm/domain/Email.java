package com.cm.domain;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Ievgen on 2/4/2015.
 */
@Entity
@Table(name = "CM_T_EMAILS", schema = "CM", catalog = "")
public class Email {
    private long id;
    private String email;
    private String body;
    private BigInteger sent;
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
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "BODY")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "SENT")
    public BigInteger getSent() {
        return sent;
    }

    public void setSent(BigInteger sent) {
        this.sent = sent;
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

        Email cmTEmails = (Email) o;

        if (createdat != cmTEmails.createdat) return false;
        if (id != cmTEmails.id) return false;
        if (body != null ? !body.equals(cmTEmails.body) : cmTEmails.body != null) return false;
        if (email != null ? !email.equals(cmTEmails.email) : cmTEmails.email != null) return false;
        if (rowstatus != null ? !rowstatus.equals(cmTEmails.rowstatus) : cmTEmails.rowstatus != null) return false;
        if (sent != null ? !sent.equals(cmTEmails.sent) : cmTEmails.sent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (sent != null ? sent.hashCode() : 0);
        result = 31 * result + (int) (createdat ^ (createdat >>> 32));
        result = 31 * result + (rowstatus != null ? rowstatus.hashCode() : 0);
        return result;
    }
}
