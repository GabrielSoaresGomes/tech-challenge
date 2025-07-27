CREATE TABLE addresses (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id      BIGINT NOT NULL,
    street       VARCHAR(255) NOT NULL,
    number       VARCHAR(255) NOT NULL,
    complement   VARCHAR(255),
    neighborhood VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    state        VARCHAR(255) NOT NULL,
    country      VARCHAR(255) NOT NULL,
    postal_code  VARCHAR(255) NOT NULL,
    last_modified_date_time TIMESTAMP NOT NULL,
    CONSTRAINT address_user_FK FOREIGN KEY (user_id) REFERENCES users (id)
);
