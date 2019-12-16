package ru.iovchinnikov.mailing.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "MAILING_CONTENTS")
@Entity(name = "mailing_Contents")
public class Contents extends StandardEntity {
    private static final long serialVersionUID = -1687344864699388906L;

    @NotNull
    @Lob
    @Column(name = "TEXT", nullable = false)
    protected String text;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contents", optional = false)
    protected Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}