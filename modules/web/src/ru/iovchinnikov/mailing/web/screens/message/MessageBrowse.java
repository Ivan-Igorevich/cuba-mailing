package ru.iovchinnikov.mailing.web.screens.message;

import com.haulmont.cuba.gui.screen.*;
import ru.iovchinnikov.mailing.entity.Message;

@UiController("mailing_Message.browse")
@UiDescriptor("message-browse.xml")
@LookupComponent("messagesTable")
@LoadDataBeforeShow
public class MessageBrowse extends StandardLookup<Message> {
}