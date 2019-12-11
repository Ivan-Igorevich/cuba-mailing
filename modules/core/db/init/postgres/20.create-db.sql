-- begin MAILING_MESSAGE
alter table MAILING_MESSAGE add constraint FK_MAILING_MESSAGE_ON_SENDER foreign key (SENDER_ID) references SEC_USER(ID)^
alter table MAILING_MESSAGE add constraint FK_MAILING_MESSAGE_ON_RECIPIENT foreign key (RECIPIENT_ID) references SEC_USER(ID)^
alter table MAILING_MESSAGE add constraint FK_MAILING_MESSAGE_ON_META foreign key (META_ID) references MAILING_META_INFO(ID)^
alter table MAILING_MESSAGE add constraint FK_MAILING_MESSAGE_ON_CONTENTS foreign key (CONTENTS_ID) references MAILING_CONTENTS(ID)^
create index IDX_MAILING_MESSAGE_ON_SENDER on MAILING_MESSAGE (SENDER_ID)^
create index IDX_MAILING_MESSAGE_ON_RECIPIENT on MAILING_MESSAGE (RECIPIENT_ID)^
create index IDX_MAILING_MESSAGE_ON_META on MAILING_MESSAGE (META_ID)^
create index IDX_MAILING_MESSAGE_ON_CONTENTS on MAILING_MESSAGE (CONTENTS_ID)^
-- end MAILING_MESSAGE