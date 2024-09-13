CREATE TABLE public.usr
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name    VARCHAR(255)                            NOT NULL,
    surname VARCHAR(255)                            NOT NULL,
    phone   VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_usr PRIMARY KEY (id)
);

ALTER TABLE public.usr
    ADD CONSTRAINT uc_usr_phone UNIQUE (phone);