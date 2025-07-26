CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255),
    login       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    last_modified_date_time TIMESTAMP NOT NULL,
    CONSTRAINT uk_users_login UNIQUE (login)
);
