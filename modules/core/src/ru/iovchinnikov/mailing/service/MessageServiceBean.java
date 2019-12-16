package ru.iovchinnikov.mailing.service;

import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
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
    public void send(User sender, User recipient, String subject, String text) {
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

        commitContext.addInstanceToCommit(msg, "message-view");
        commitContext.addInstanceToCommit(meta, "metaInfo-view");
        commitContext.addInstanceToCommit(contents, "contents-view");
        dataManager.commit(commitContext);
    }
}