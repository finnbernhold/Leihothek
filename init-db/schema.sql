create table offer
(
    id                 SERIAL    PRIMARY KEY,
    title              TEXT      NOT NULL,
    description        TEXT      NOT NULL,
    image_id             INTEGER
);

create table image
(
    id        SERIAL PRIMARY KEY,
    title     TEXT  NOT NULL,
    mime_type TEXT  NOT NULL,
    image     BYTEA NOT NULL
);