CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255),
    login       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    address     VARCHAR(255),
    lastModifiedDateTime TIMESTAMP NOT NULL,
    CONSTRAINT uk_users_login UNIQUE (login)
);

CREATE TABLE IF NOT EXISTS addresses (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    userId       BIGINT NOT NULL,
    street       VARCHAR(255) NOT NULL,
    number       VARCHAR(255) NOT NULL,
    complement   VARCHAR(255),
    neighborhood VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    state        VARCHAR(255) NOT NULL,
    country      VARCHAR(255) NOT NULL,
    postalCode   VARCHAR(255) NOT NULL,
    lastModifiedDateTime TIMESTAMP NOT NULL,
    CONSTRAINT address_user_FK FOREIGN KEY (userId) REFERENCES users (id)
);
