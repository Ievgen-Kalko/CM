package com.cm.domain.model;

import com.cm.domain.AbstractAuditableEntity;

import javax.persistence.*;

@Entity
@Table(name = "CM_T_EMAILS")
public class Email extends AbstractAuditableEntity {

    public static enum Columns {
        ID("id"),
        FROM_("from"),
        TO_("to"),
        SUBJECT("subject"),
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

    @Column(name = "FROM_")
    private String from;

    @Column(name = "TO_")
    private String to;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "BODY")
    private String body;

    @Column(name = "SENT")
    @Enumerated(EnumType.ORDINAL)
    private SentStatus sentStatus;

    public Email() {
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {

        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSentStatus(SentStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public String getBody() {
        return body;
    }

    public SentStatus getSentStatus() {
        return sentStatus;
    }

    public enum SentStatus {
        DELIVERY_FAILED(0L),
        SUCCESSFULLY_SENT(1L);

        private long value;

        private SentStatus(long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }
    }
}
