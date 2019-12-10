package ru.iovchinnikov.mailing.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "MAILING_META_INFO")
@Entity(name = "mailing_MetaInfo")
public class MetaInfo extends StandardEntity {
    private static final long serialVersionUID = 7962287168725966201L;

    @NotNull
    @Column(name = "SENT", nullable = false)
    protected Boolean sent = false;

    @NotNull
    @Column(name = "IS_READ", nullable = false)
    protected Boolean isRead = false;

    @NotNull
    @Column(name = "DELETED_BY_SENDER", nullable = false)
    protected Boolean deletedBySender = false;

    @Column(name = "DELETED_BY_RECIPIENT") protected Boolean deletedByRecipient;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "meta", optional = false) protected Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Boolean getDeletedByRecipient() {
        return deletedByRecipient;
    }

    public void setDeletedByRecipient(Boolean deletedByRecipient) {
        this.deletedByRecipient = deletedByRecipient;
    }

    public Boolean getDeletedBySender() {
        return deletedBySender;
    }

    public void setDeletedBySender(Boolean deletedBySender) {
        this.deletedBySender = deletedBySender;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}