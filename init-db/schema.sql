CREATE TABLE offer
(
    id                 SERIAL    PRIMARY KEY,
    title              TEXT      NOT NULL,
    description        TEXT      NOT NULL,
    category           TEXT      NOT NULL,
    image_id           INTEGER,
    created_by         TEXT     NOT NULL
);

CREATE TABLE image
(
    id        SERIAL PRIMARY KEY,
    mime_type TEXT  NOT NULL,
    image     BYTEA NOT NULL


);

CREATE TABLE users
(
   username TEXT    NOT NULL,
   password TEXT    NOT NULL,
   enabled  BOOLEAN NOT NULL,
   PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  TEXT NOT NULL,
    authority TEXT NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username
    on authorities (username, authority);

INSERT INTO users (username, password, enabled)
  values ('admin',
    '{bcrypt}$2a$10$rBIL6NvjZ6gLniI2xaMVCuUJd5Ee0zRrMqqAyBLQ7OQhNIv3FFRFa',
    TRUE);

INSERT INTO authorities (username, authority)
  values ('admin', 'ADMIN');