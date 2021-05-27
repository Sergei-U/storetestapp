CREATE TABLE USR
(
    ID       VARCHAR(300) PRIMARY KEY NOT NULL,
    usr_name VARCHAR(256),
    userpic  VARCHAR(256),
    email    VARCHAR(256),
    gender   VARCHAR(256),
    locale   VARCHAR(256)
);

CREATE TABLE PRODUCT
(
    ID           BIGINT PRIMARY KEY  NOT NULL,
    category     bigint              NOT NULL,
    img          VARCHAR(220)        NOT NULL,
    NAME         VARCHAR(300) unique NOT NULL,
    description  INTEGER             NOT NULL,
    price        INTEGER             NOT NULL,
    quantity     INTEGER             NOT NULL,
    creationDate DATE                NOT NULL
);

CREATE TABLE CATEGORY
(
    ID           BIGINT PRIMARY KEY  NOT NULL,
    category     bigint              NOT NULL,
    NAME         VARCHAR(300) unique NOT NULL,
    description  INTEGER             NOT NULL,
    creationDate DATE                NOT NULL
);

