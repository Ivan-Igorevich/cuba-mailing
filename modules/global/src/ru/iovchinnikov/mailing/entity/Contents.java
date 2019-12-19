package ru.iovchinnikov.mailing.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s|text")
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

    @JoinTable(name = "MAILING_CONTENTS_FILE_DESCRIPTOR_LINK",
            joinColumns = @JoinColumn(name = "CONTENTS_ID"),
            inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @ManyToMany
    protected List<FileDescriptor> attachments;

    public List<FileDescriptor> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<FileDescriptor> attachments) {
        this.attachments = attachments;
    }

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