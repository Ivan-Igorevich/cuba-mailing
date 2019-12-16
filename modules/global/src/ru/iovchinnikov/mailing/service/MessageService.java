package ru.iovchinnikov.mailing.service;

import com.haulmont.cuba.security.entity.User;

public interface MessageService {
    String NAME = "mailing_MessageService";

    void send(User sender, User recipient, String subject, String text);
}