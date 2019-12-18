package ru.iovchinnikov.mailing.service;

import com.haulmont.cuba.security.entity.FilterEntity;
import com.haulmont.cuba.security.entity.User;

public interface FilterService {
    String NAME = "mailing_FilterService";

    FilterEntity getEmptyFilter();
    FilterEntity getSenderFilter(User user);
    FilterEntity getReceiverFilter(User user);

}