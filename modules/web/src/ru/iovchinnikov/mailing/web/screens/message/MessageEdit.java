package ru.iovchinnikov.mailing.web.screens.message;

import com.haulmont.cuba.gui.screen.*;
import ru.iovchinnikov.mailing.entity.Message;

@UiController("mailing_Message.edit")
@UiDescriptor("message-edit.xml")
@EditedEntityContainer("messageDc")
@LoadDataBeforeShow
public class MessageEdit extends StandardEditor<Message> {
}