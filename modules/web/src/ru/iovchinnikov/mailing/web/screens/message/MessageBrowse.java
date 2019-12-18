package ru.iovchinnikov.mailing.web.screens.message;

import com.haulmont.cuba.gui.components.Filter;
import com.haulmont.cuba.gui.components.filter.FilterHelper;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import ru.iovchinnikov.mailing.config.SystemUserConfig;
import ru.iovchinnikov.mailing.entity.Message;
import ru.iovchinnikov.mailing.service.FilterService;
import ru.iovchinnikov.mailing.service.MessageService;

import javax.inject.Inject;

@UiController("mailing_Message.browse")
@UiDescriptor("message-browse.xml")
@LookupComponent("messagesTable")
@LoadDataBeforeShow
public class MessageBrowse extends StandardLookup<Message> {
    @Inject private MessageService messageService;
    @Inject private SystemUserConfig systemUserConfig;
    @Inject private UserSession userSession;
    @Inject private CollectionLoader<Message> messagesDl;
    @Inject private Filter filter;
    @Inject private FilterService filterService;


    public void checkService() {
        messageService.send(
                systemUserConfig.getSystemUser(),
                userSession.getCurrentOrSubstitutedUser(),
                "Service testing.",
                "This is a test message.");
        messagesDl.load();
    }

    public void showAll() {
        filter.setFilterEntity(filterService.getEmptyFilter());
        filter.apply(true);
    }

    public void showInbox() {
        filter.setFilterEntity(filterService.getReceiverFilter(userSession.getUser()));
        filter.apply(true);
    }

    public void showSent() {
        filter.setFilterEntity(filterService.getSenderFilter(userSession.getUser()));
        filter.apply(true);
    }
}