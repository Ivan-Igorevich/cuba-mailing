package ru.iovchinnikov.mailing.web.screens.message;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstancePropertyContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import ru.iovchinnikov.mailing.entity.Contents;
import ru.iovchinnikov.mailing.entity.Message;
import ru.iovchinnikov.mailing.entity.MetaInfo;

import javax.inject.Inject;

@UiController("mailing_Message.edit")
@UiDescriptor("message-edit.xml")
@EditedEntityContainer("messageDc")
@LoadDataBeforeShow
public class MessageEdit extends StandardEditor<Message> {
    @Inject private CollectionLoader<User> userListDl;
    @Inject private UserSessionSource userSessionSource;
    @Inject private Metadata metadata;
    @Inject private DataContext dataContext;

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        User currentUser = userSessionSource.getUserSession().getUser();
        userListDl.setParameter("currentUser", currentUser.getLogin());         // load all users to a list of recipients
        getEditedEntity().setSender(currentUser);                               // auto set current user as a sender
        getScreenData().loadAll();
    }

    @Subscribe
    private void onInitEntity(InitEntityEvent<Message> event) {
        Message msg = event.getEntity();
        msg.setContents(createContent());
        msg.setMeta(createMeta());
    }

    private Contents createContent() {
        Contents contents = metadata.create(Contents.class);
        return dataContext.merge(contents);
    }

    private MetaInfo createMeta() {
        MetaInfo meta = metadata.create(MetaInfo.class);
        meta.initNewItem();
        return dataContext.merge(meta);
    }



}