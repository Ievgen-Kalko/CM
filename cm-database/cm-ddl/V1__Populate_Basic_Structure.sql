------------------------------------------------------------------------------------------------------------------------
--                                                COINS MANAGEMENT APP
--                                            POPULATE BASIC TABLES STRUCTURE
------------------------------------------------------------------------------------------------------------------------

-- 1. Populate tables for basic entities
--
-- Populate CM_T_COINS table
CREATE TABLE CM_T_COINS (
    ID          INTEGER       not null,
    DESCRIPTION VARCHAR2(255) not null,
    COMPOSITION NUMBER(1)     not null,
    COUNTRY     VARCHAR2(3)     not null,
    YEAR        VARCHAR2(4)   not null,
    CIRCULATION INTEGER       not null,
    GRADE       NUMBER(1)     not null,
    PRICE       NUMBER(35,2)      null,
    CREATEDAT   TIMESTAMP     not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_COINS_PK PRIMARY KEY (ID)
);

-- Populate CM_T_USERS table
CREATE TABLE CM_T_USERS (
    ID          INTEGER       not null,
    TYPE        VARCHAR2(30)  not null,
    EMAIL       VARCHAR2(255) not null,
    CREATEDAT   TIMESTAMP     not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_USERS_PK PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX CM_T_USERS_EMAIL_IDX ON CM_T_USERS (EMAIL);

-- Populate CM_T_EMAILS table
CREATE TABLE CM_T_EMAILS (
    ID          INTEGER       not null,
    EMAIL       VARCHAR2(255) not null,
    BODY        CLOB          not null,
    SENT        NUMBER(1)     not null,
    CREATEDAT   TIMESTAMP     not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_EMAILS_PK PRIMARY KEY (ID)
);

-- Populate CM_T_SUBSCRIPTS table
CREATE TABLE CM_T_SUBSCRIPTS (
    ID          INTEGER       not null,
    USERID      INTEGER       not null,
    COUNTRY     VARCHAR2(3)   not null,
    CREATEDAT   TIMESTAMP     not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_SUBSCRIPTS_PK PRIMARY KEY (ID),
    CONSTRAINT CM_T_SUBSCRIPTS_USERS_FK FOREIGN KEY (USERID) REFERENCES CM_T_USERS (ID)
);

-- 2. Create sequence
--
-- Create CM_T_SEQUENCE
CREATE SEQUENCE CM_T_SEQUENCE
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;