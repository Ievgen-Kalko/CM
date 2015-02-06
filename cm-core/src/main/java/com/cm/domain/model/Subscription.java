package com.cm.domain.model;

import com.cm.domain.AbstractAuditableEntity;

import javax.persistence.*;

@Entity
@Table(name = "CM_T_SUBSCRIPTS")
public class Subscription extends AbstractAuditableEntity {

    public static enum Columns {
        ID("id"),
        USERID("userid"),
        COUNTRY("country"),
        CREATEDAT("createdat"),
        ROWSTATUS("rowstatus");

        private final String columnName;

        private Columns(final String columnName) {
            this.columnName = columnName;
        }

        @Override
        public String toString() {
            return this.columnName;
        }
    }

    @Column(name = "COUNTRY")
    private String country;

    @ManyToOne
    @JoinColumn(name = "USERID")
    private User userId;

    public Subscription() {
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public User getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (!country.equals(that.country)) return false;
        if (!userId.equals(that.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }
}
