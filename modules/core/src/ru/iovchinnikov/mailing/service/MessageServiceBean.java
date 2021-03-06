package ru.iovchinnikov.mailing.service;

import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;
import ru.iovchinnikov.mailing.entity.Contents;
import ru.iovchinnikov.mailing.entity.Message;
import ru.iovchinnikov.mailing.entity.MetaInfo;

import javax.inject.Inject;

@Service(MessageService.NAME)
public class MessageServiceBean implements MessageService {

    @Inject private DataManager dataManager;

    @Override
    public long getUnreadMsgCount(User user) {
        LoadContext<Message> ctx = LoadContext.create(Message.class);
        ctx.setQuery(LoadContext.createQuery("select e from mailing_Message e where e.recipient.id = :current and e.meta.isRead = false")
                .setParameter("current", user.getId()));
        return dataManager.getCount(ctx);
    }

    @Override
    public void send(User sender, User recipient, String subject, String text) {
        // To send a message we need to create it and put it to the same context with related entities
        CommitContext commitContext = new CommitContext();
        MetaInfo meta = dataManager.create(MetaInfo.class);
        Contents contents = dataManager.create(Contents.class);
        Message msg = dataManager.create(Message.class);

        msg.setRecipient(recipient);
        msg.setSender(sender);
        msg.setSubject(subject);
        msg.setMeta(meta);
        msg.setContents(contents);

        contents.setText(text);
        contents.setMessage(msg);

        meta.initNewItem();
        meta.setSent(true);
        meta.setMessage(msg);

        // As we created entities and filled them with data, we put them to context and commit in one transaction
        commitContext.addInstanceToCommit(msg, "message-view");
        commitContext.addInstanceToCommit(meta, "metaInfo-view");
        commitContext.addInstanceToCommit(contents, "contents-view");
        dataManager.commit(commitContext);
    }
}