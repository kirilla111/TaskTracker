create TABLE USERS
(
    id            NUMBER PRIMARY KEY,
    login         VARCHAR2(255) NOT NULL UNIQUE,
    first_name    VARCHAR2(255) NOT NULL,
    patronymic    VARCHAR2(255),
    last_name     VARCHAR2(255) NOT NULL,
    password      VARCHAR2(255) NOT NULL,
    email         VARCHAR(255)  NOT NULL UNIQUE
);

create sequence sq_user_id start with 1 increment by 1;


insert into USERS (id, login, first_name, patronymic, last_name, password, email)
    values (next value for sq_user_id, 'u1', 'f1', null, 'l1', 'p1', 'u1@mail.ru')

create TABLE USER_DESK
(
    USER_ID            BIGINT,
   	DESK_ID 			BIGINT
);


create TABLE DESKS
(
    id            BIGINT PRIMARY KEY,
    title 		  VARCHAR(255) NOT NULL UNIQUE,
    description 		  VARCHAR(255) NOT NULL UNIQUE,
    owner_id	  BIGINT not null
);

create sequence sq_desk_id start with 1 increment by 1;
select nextval ('sq_desk_id')

alter table DESK
add CONSTRAINT owner_id
FOREIGN KEY (owner_id)
REFERENCES USERS (id);