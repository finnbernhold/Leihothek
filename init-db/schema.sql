create table offer
(
    id                 SERIAL    PRIMARY KEY,
    title              TEXT      NOT NULL,
    description        TEXT      NOT NULL
);

create table image
(
    id          SERIAL  PRIMARY KEY,
    mime_type   TEXT    NOT NULL,
    image       BYTEA   NOT NULL
);