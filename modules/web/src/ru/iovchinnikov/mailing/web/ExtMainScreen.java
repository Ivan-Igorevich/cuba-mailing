package ru.iovchinnikov.mailing.web;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.SizeUnit;
import com.haulmont.cuba.gui.components.SplitPanel;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.components.mainwindow.AppWorkArea;
import com.haulmont.cuba.gui.components.mainwindow.FoldersPane;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.WebConfig;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.haulmont.cuba.web.widgets.CubaHorizontalSplitPanel;
import com.vaadin.server.Sizeable;
import ru.iovchinnikov.mailing.service.MessageService;
import ru.iovchinnikov.mailing.web.screens.message.MessageBrowse;

import javax.inject.Inject;


@UiController("topMenuMainScreen")
@UiDescriptor("ext-main-screen.xml")
public class ExtMainScreen extends MainScreen {
    @Inject private SplitPanel foldersSplit;
    @Inject private FoldersPane foldersPane;
    @Inject private AppWorkArea workArea;
    @Inject private WebConfig webConfig;
    @Inject private MessageService messageService;
    @Inject private UserSession userSession;
    @Inject private Action actOpenMsg;
    @Inject private ScreenBuilders screenBuilders;
    @Inject private Screens screens;

    public ExtMainScreen() {
        addInitListener(this::initLayout);
    }

    protected void initLayout(@SuppressWarnings("unused") InitEvent event) {
        if (webConfig.getFoldersPaneEnabled()) {
            if (webConfig.getFoldersPaneVisibleByDefault()) {
                foldersSplit.setSplitPosition(webConfig.getFoldersPaneDefaultWidth(), SizeUnit.PIXELS);
            } else {
                foldersSplit.setSplitPosition(0);
            }
            CubaHorizontalSplitPanel vSplitPanel = (CubaHorizontalSplitPanel) WebComponentsHelper.unwrap(foldersSplit);
            vSplitPanel.setDefaultPosition(webConfig.getFoldersPaneDefaultWidth() + "px");
            vSplitPanel.setMaxSplitPosition(50, Sizeable.Unit.PERCENTAGE);
            vSplitPanel.setDockable(true);
        } else {
            foldersPane.setEnabled(false);
            foldersPane.setVisible(false);
            foldersSplit.remove(workArea);
            int foldersSplitIndex = getWindow().indexOf(foldersSplit);
            getWindow().remove(foldersSplit);
            getWindow().add(workArea, foldersSplitIndex);
            getWindow().expand(workArea);
        }
    }

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        checkMessages();
    }

    @Subscribe("actOpenMsg")
    private void onActOpenMsg(Action.ActionPerformedEvent event) {
        screens.create(MessageBrowse.class).show();
    }

    public void updateMessages(Timer source) {
        checkMessages();
    }

    private void checkMessages() {
        long msgCount = messageService.getUnreadMsgCount(userSession.getUser());
        String msgCountCaption = (msgCount > 0) ? String.format("(%d)", msgCount) : "";
        actOpenMsg.setCaption(msgCountCaption);
    }


}