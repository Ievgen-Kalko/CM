package com.cm.domain.model;

import com.cm.domain.AbstractAuditableEntity;

import javax.persistence.*;

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
    @Enumerated(EnumType.ORDINAL)
    private UserTypes type;

    @Column(name = "EMAIL")
    private String email;

    public User() {
    }

    public UserTypes getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public void setType(UserTypes type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static enum UserTypes {
        CLIENT(0L),
        ADMIN(1L);

        private final long value;

        private UserTypes(final long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }
    }
}
