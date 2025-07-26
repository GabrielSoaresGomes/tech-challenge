CREATE TABLE user_address (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,

    CONSTRAINT fk_users_addresses_user_id
        FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_users_addresses_address_id
        FOREIGN KEY (address_id) REFERENCES addresses(id)
);
