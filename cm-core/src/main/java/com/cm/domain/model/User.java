package com.cm.domain.model;

import com.cm.domain.AbstractAuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ievgen on 2/4/2015.
 */
@Entity
@Table(name = "CM_T_USERS", schema = "CM", catalog = "")
public class User extends AbstractAuditableEntity {

    public static enum Columns {
        ID("id"),
        TYPE("type"),
        EMAIL("email"),
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

    @Column(name = "TYPE")
    private String type;

    @Column(name = "EMAIL")
    private String email;

    public User() {
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
