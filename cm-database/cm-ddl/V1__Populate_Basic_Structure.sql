------------------------------------------------------------------------------------------------------------------------
--                                                COINS MANAGEMENT APP
--                                            POPULATE BASIC TABLES STRUCTURE
------------------------------------------------------------------------------------------------------------------------

-- 1. Populate dictionaries.
--
-- Populate CM_T_COMPOSITIONS table
CREATE TABLE CM_T_COMPOSITIONS (
    ID          INTEGER       not null,
    VALUE       VARCHAR2(30)  not null,
    CREATEDAT   INTEGER       not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_COMPOSITIONS_PK PRIMARY KEY (ID)
);

-- Populate CM_T_GRADES table
CREATE TABLE CM_T_GRADES (
    ID          INTEGER       not null,
    VALUE       VARCHAR2(30)  not null,
    CREATEDAT   INTEGER       not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_GRADES_PK PRIMARY KEY (ID)
);

-- Populate CM_T_COUNTRIES table
CREATE TABLE CM_T_COUNTRIES (
    ID          INTEGER       not null,
    VALUE       VARCHAR2(30)  not null,
    CREATEDAT   INTEGER       not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_COUNTRIES_PK PRIMARY KEY (ID)
);

-- 2. Populate tables for basic entities
--
-- Populate CM_T_COINS table
CREATE TABLE CM_T_COINS (
    ID          INTEGER       not null,
    DESCRIPTION VARCHAR2(255) not null,
    COMPOSITION INTEGER       not null,
    COUNTRY     INTEGER       not null,
    YEAR        VARCHAR2(4)   not null,
    CIRCULATION INTEGER       not null,
    GRADE       INTEGER       not null,
    PRICE       NUMBER(16,2)      null,
    CREATEDAT   INTEGER       not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_COINS_PK PRIMARY KEY (ID),
    CONSTRAINT CM_T_COINS_COUNTRIES_FK FOREIGN KEY (COUNTRY) REFERENCES CM_T_COUNTRIES (ID),
    CONSTRAINT CM_T_COINS_COMPOSITIONS_FK FOREIGN KEY (COMPOSITION) REFERENCES CM_T_COMPOSITIONS (ID),
    CONSTRAINT CM_T_COINS_GRADES_FK FOREIGN KEY (GRADE) REFERENCES CM_T_GRADES (ID)
);

-- Populate CM_T_USERS table
CREATE TABLE CM_T_USERS (
    ID          INTEGER       not null,
    TYPE        VARCHAR2(30)  not null,
    EMAIL       VARCHAR2(255) not null,
    CREATEDAT   INTEGER       not null,
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
    CREATEDAT   INTEGER       not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_EMAILS_PK PRIMARY KEY (ID)
);

-- Populate CM_T_SUBSCRIPTS table
CREATE TABLE CM_T_SUBSCRIPTS (
    ID          INTEGER       not null,
    USERID      INTEGER       not null,
    COUNTRY     INTEGER       not null,
    CREATEDAT   INTEGER       not null,
    ROWSTATUS   NUMBER(1)     not null,
    CONSTRAINT CM_T_SUBSCRIPTS_PK PRIMARY KEY (ID),
    CONSTRAINT CM_T_SUBSCRIPTS_USERS_FK FOREIGN KEY (USERID) REFERENCES CM_T_USERS (ID),
    CONSTRAINT CM_T_SUBSCRIPTS_COUNT_FK FOREIGN KEY (COUNTRY) REFERENCES CM_T_COUNTRIES (ID)
);