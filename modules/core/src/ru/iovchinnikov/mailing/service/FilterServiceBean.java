package ru.iovchinnikov.mailing.service;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.FilterEntity;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(FilterService.NAME)
public class FilterServiceBean implements FilterService {
    @Inject private Metadata metadata;
    @Inject private Messages messages;

    @Override
    public FilterEntity getEmptyFilter() {
        FilterEntity filterEntity = metadata.create(FilterEntity.class);
        filterEntity.setName(messages.getMainMessage("All messages"));
        filterEntity.setXml(null);
        return filterEntity;
    }

    @Override
    public FilterEntity getSenderFilter(User user) {
        FilterEntity filterEntity = metadata.create(FilterEntity.class);
        filterEntity.setName(messages.getMainMessage("Sent by me"));
        String filterXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<filter>\n" +
                "  <and>\n" +
                "    <c name=\"sender\" class=\"com.haulmont.cuba.security.entity.User\" hidden=\"true\" required=\"true\" operatorType=\"EQUAL\" width=\"1\" type=\"PROPERTY\"><![CDATA[e.sender.id = :component$filter.sender40943]]>\n" +
                "      <param name=\"component$filter.sender40943\" javaClass=\"com.haulmont.cuba.security.entity.User\">" + user.getId() + "</param>\n" +
                "    </c>\n" +
                "  </and>\n" +
                "</filter>\n";
        filterEntity.setXml(filterXml);
        return filterEntity;
    }

    @Override
    public FilterEntity getReceiverFilter(User user) {
        FilterEntity filterEntity = metadata.create(FilterEntity.class);
        filterEntity.setName(messages.getMainMessage("Received by me"));
        String filterXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<filter>\n" +
                "  <and>\n" +
                "    <c name=\"recipient\" class=\"com.haulmont.cuba.security.entity.User\" hidden=\"true\" required=\"true\" operatorType=\"EQUAL\" width=\"1\" type=\"PROPERTY\"><![CDATA[e.recipient.id = :component$filter.recipient91151]]>\n" +
                "      <param name=\"component$filter.recipient91151\" javaClass=\"com.haulmont.cuba.security.entity.User\">" + user.getId() + "</param>\n" +
                "    </c>\n" +
                "  </and>\n" +
                "</filter>\n";
        filterEntity.setXml(filterXml);
        return filterEntity;
    }
}
