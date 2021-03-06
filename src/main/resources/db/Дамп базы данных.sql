PGDMP     -    !                z            postgres    14.2    14.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    13754    postgres    DATABASE     e   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE postgres;
                postgres    false                       0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3343                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            ?            1259    16433    desk_pillar    TABLE     N   CREATE TABLE public.desk_pillar (
    desk_id bigint,
    pillar_id bigint
);
    DROP TABLE public.desk_pillar;
       public         heap    postgres    false    3            ?            1259    16413    desks    TABLE     ?   CREATE TABLE public.desks (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    owner_id bigint NOT NULL
);
    DROP TABLE public.desks;
       public         heap    postgres    false    3            ?            1259    16430    pillar    TABLE     d   CREATE TABLE public.pillar (
    id bigint,
    title character varying(255),
    desk_id bigint
);
    DROP TABLE public.pillar;
       public         heap    postgres    false    3            ?            1259    16424 
   sq_desk_id    SEQUENCE     s   CREATE SEQUENCE public.sq_desk_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.sq_desk_id;
       public          postgres    false    3            ?            1259    16436    sq_pillar_id    SEQUENCE     u   CREATE SEQUENCE public.sq_pillar_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.sq_pillar_id;
       public          postgres    false    3            ?            1259    16409 
   sq_user_id    SEQUENCE     s   CREATE SEQUENCE public.sq_user_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.sq_user_id;
       public          postgres    false    3            ?            1259    16410 	   user_desk    TABLE     \   CREATE TABLE public.user_desk (
    user_id bigint NOT NULL,
    desk_id bigint NOT NULL
);
    DROP TABLE public.user_desk;
       public         heap    postgres    false    3            ?            1259    16398    users    TABLE     @  CREATE TABLE public.users (
    id bigint NOT NULL,
    login character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    patronymic character varying(255),
    last_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false    3                      0    16433    desk_pillar 
   TABLE DATA           9   COPY public.desk_pillar (desk_id, pillar_id) FROM stdin;
    public          postgres    false    215                      0    16413    desks 
   TABLE DATA           A   COPY public.desks (id, title, description, owner_id) FROM stdin;
    public          postgres    false    212                      0    16430    pillar 
   TABLE DATA           4   COPY public.pillar (id, title, desk_id) FROM stdin;
    public          postgres    false    214                      0    16410 	   user_desk 
   TABLE DATA           5   COPY public.user_desk (user_id, desk_id) FROM stdin;
    public          postgres    false    211                      0    16398    users 
   TABLE DATA           ^   COPY public.users (id, login, first_name, patronymic, last_name, password, email) FROM stdin;
    public          postgres    false    209                       0    0 
   sq_desk_id    SEQUENCE SET     9   SELECT pg_catalog.setval('public.sq_desk_id', 65, true);
          public          postgres    false    213                       0    0    sq_pillar_id    SEQUENCE SET     <   SELECT pg_catalog.setval('public.sq_pillar_id', 113, true);
          public          postgres    false    216                       0    0 
   sq_user_id    SEQUENCE SET     8   SELECT pg_catalog.setval('public.sq_user_id', 5, true);
          public          postgres    false    210            u           2606    16419    desks desk_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.desks
    ADD CONSTRAINT desk_pkey PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.desks DROP CONSTRAINT desk_pkey;
       public            postgres    false    212            o           2606    16408    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    209            q           2606    16406    users users_login_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_login_key;
       public            postgres    false    209            s           2606    16404    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    209            v           2606    16425    desks owner_id    FK CONSTRAINT     n   ALTER TABLE ONLY public.desks
    ADD CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES public.users(id);
 8   ALTER TABLE ONLY public.desks DROP CONSTRAINT owner_id;
       public          postgres    false    209    3187    212               J   x?%̹?@A?Fu<??\???????r?S"M???K ???ˋ??.5?A??Ŗ???wz??ԙ???= ~+b?         \   x?35?IL?IU(?,?P(???I,*??I,.Q(N,KU(I-.?4?23?*K?/R.K-IDS????Rg????BSt?F8c?!Pe? q?A?         p   x??0???S(?O/J-.???㲰???u??-9]??RALKt???I#?Kc??	??)?~C#?JCcLi$݆X??!˛#?e?Ķ??gh??????? C??qqq ϼO         .   x?3?45?2?43?2? ?5??Ab? .X?)?"?:b???? ? #         X   x?3?,5?L3?????1?,0rr3s??J??8K?9C?S???H?1\?c???L8??RK?$'HCv.?U?c
?7ĥ??+F??? ?,+?                     0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    13754    postgres    DATABASE     e   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE postgres;
                postgres    false                       0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3343                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            ?            1259    16433    desk_pillar    TABLE     N   CREATE TABLE public.desk_pillar (
    desk_id bigint,
    pillar_id bigint
);
    DROP TABLE public.desk_pillar;
       public         heap    postgres    false    3            ?            1259    16413    desks    TABLE     ?   CREATE TABLE public.desks (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    owner_id bigint NOT NULL
);
    DROP TABLE public.desks;
       public         heap    postgres    false    3            ?            1259    16430    pillar    TABLE     d   CREATE TABLE public.pillar (
    id bigint,
    title character varying(255),
    desk_id bigint
);
    DROP TABLE public.pillar;
       public         heap    postgres    false    3            ?            1259    16424 
   sq_desk_id    SEQUENCE     s   CREATE SEQUENCE public.sq_desk_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.sq_desk_id;
       public          postgres    false    3            ?            1259    16436    sq_pillar_id    SEQUENCE     u   CREATE SEQUENCE public.sq_pillar_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.sq_pillar_id;
       public          postgres    false    3            ?            1259    16409 
   sq_user_id    SEQUENCE     s   CREATE SEQUENCE public.sq_user_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.sq_user_id;
       public          postgres    false    3            ?            1259    16410 	   user_desk    TABLE     \   CREATE TABLE public.user_desk (
    user_id bigint NOT NULL,
    desk_id bigint NOT NULL
);
    DROP TABLE public.user_desk;
       public         heap    postgres    false    3            ?            1259    16398    users    TABLE     @  CREATE TABLE public.users (
    id bigint NOT NULL,
    login character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    patronymic character varying(255),
    last_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false    3                      0    16433    desk_pillar 
   TABLE DATA           9   COPY public.desk_pillar (desk_id, pillar_id) FROM stdin;
    public          postgres    false    215   1                 0    16413    desks 
   TABLE DATA           A   COPY public.desks (id, title, description, owner_id) FROM stdin;
    public          postgres    false    212   T                  0    16430    pillar 
   TABLE DATA           4   COPY public.pillar (id, title, desk_id) FROM stdin;
    public          postgres    false    214   f                  0    16410 	   user_desk 
   TABLE DATA           5   COPY public.user_desk (user_id, desk_id) FROM stdin;
    public          postgres    false    211   z                  0    16398    users 
   TABLE DATA           ^   COPY public.users (id, login, first_name, patronymic, last_name, password, email) FROM stdin;
    public          postgres    false    209   8                   0    0 
   sq_desk_id    SEQUENCE SET     9   SELECT pg_catalog.setval('public.sq_desk_id', 65, true);
          public          postgres    false    213                       0    0    sq_pillar_id    SEQUENCE SET     <   SELECT pg_catalog.setval('public.sq_pillar_id', 113, true);
          public          postgres    false    216                       0    0 
   sq_user_id    SEQUENCE SET     8   SELECT pg_catalog.setval('public.sq_user_id', 5, true);
          public          postgres    false    210            u           2606    16419    desks desk_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.desks
    ADD CONSTRAINT desk_pkey PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.desks DROP CONSTRAINT desk_pkey;
       public            postgres    false    212            o           2606    16408    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    209            q           2606    16406    users users_login_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_login_key;
       public            postgres    false    209            s           2606    16404    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    209            v           2606    16425    desks owner_id    FK CONSTRAINT     n   ALTER TABLE ONLY public.desks
    ADD CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES public.users(id);
 8   ALTER TABLE ONLY public.desks DROP CONSTRAINT owner_id;
       public          postgres    false    209    3187    212           