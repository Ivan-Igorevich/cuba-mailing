-- begin MAILING_META_INFO
create table MAILING_META_INFO (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SENT boolean not null,
    IS_READ boolean not null,
    DELETED_BY_SENDER boolean not null,
    DELETED_BY_RECIPIENT boolean,
    --
    primary key (ID)
)^
-- end MAILING_META_INFO
-- begin MAILING_CONTENTS
create table MAILING_CONTENTS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TEXT text not null,
    --
    primary key (ID)
)^
-- end MAILING_CONTENTS
-- begin MAILING_MESSAGE
create table MAILING_MESSAGE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SENDER_ID uuid,
    RECIPIENT_ID uuid not null,
    SUBJECT varchar(255) not null,
    META_ID uuid not null,
    CONTENTS_ID uuid not null,
    --
    primary key (ID)
)^
-- end MAILING_MESSAGE
