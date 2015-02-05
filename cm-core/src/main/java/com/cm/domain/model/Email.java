package com.cm.domain.model;

import com.cm.domain.AbstractAuditableEntity;

import javax.persistence.*;

@Entity
@Table(name = "CM_T_EMAILS")
public class Email extends AbstractAuditableEntity {

    public static enum Columns {
        ID("id"),
        EMAIL("email"),
        BODY("body"),
        SENT("sent"),
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

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BODY")
    private String body;

    @Column(name = "SENT")
    @Enumerated(EnumType.ORDINAL)
    private SentStatus sentStatus;

    public Email() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSentStatus(SentStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public SentStatus getSentStatus() {
        return sentStatus;
    }

    public enum SentStatus {
        SUCCESSFULLY_DELIVERED(0L),
        DELIVERY_FAILED(1L);

        private long value;

        private SentStatus(long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }
    }
}
