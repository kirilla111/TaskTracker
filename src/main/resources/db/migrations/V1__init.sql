create sequence sq_card_id start with 1 increment by 1;
create sequence sq_desk_id start with 1 increment by 1;
create sequence sq_pillar_id start with 1 increment by 1;
create sequence sq_user_id start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS public.cards
(
    id bigint,
    title character varying(100) COLLATE pg_catalog."default",
    short_description character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    pillar_id bigint,
    owner_id bigint,
    responsible_id bigint
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cards
    OWNER to postgres;

-- Table: public.desks

-- DROP TABLE IF EXISTS public.desks;

CREATE TABLE IF NOT EXISTS public.desks
(
    id bigint NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    owner_id bigint NOT NULL,
    CONSTRAINT desk_pkey PRIMARY KEY (id),
    CONSTRAINT owner_id FOREIGN KEY (owner_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.desks
    OWNER to postgres;

-- Table: public.pillar

-- DROP TABLE IF EXISTS public.pillar;

CREATE TABLE IF NOT EXISTS public.pillar
(
    id bigint,
    title character varying(255) COLLATE pg_catalog."default"
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pillar
    OWNER to postgres;

-- Table: public.user_desk

-- DROP TABLE IF EXISTS public.user_desk;

CREATE TABLE IF NOT EXISTS public.user_desk
(
    user_id bigint NOT NULL,
    desk_id bigint NOT NULL
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_desk
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    login character varying(255) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    patronymic character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_login_key UNIQUE (login)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;


-- Table: public.desk_pillar

-- DROP TABLE IF EXISTS public.desk_pillar;

CREATE TABLE IF NOT EXISTS public.desk_pillar
(
    desk_id bigint,
    pillar_id bigint
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.desk_pillar
    OWNER to postgres;