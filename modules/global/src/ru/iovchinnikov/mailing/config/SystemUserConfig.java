package ru.iovchinnikov.mailing.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.security.entity.User;

@Source(type = SourceType.DATABASE)
public interface SystemUserConfig extends Config {
    @Default("sec$User-a48ca93e-ac48-740c-4ef1-4b46833bf826")
    User getSystemUser();
}
