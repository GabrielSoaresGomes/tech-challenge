CREATE TABLE IF NOT EXISTS usersAddresses (
    userId BIGINT NOT NULL,
    addressId BIGINT NOT NULL,

    CONSTRAINT fk_users_addresses_user_id
        FOREIGN KEY (userId) REFERENCES users(id),
    CONSTRAINT fk_users_addresses_address_id
        FOREIGN KEY (addressId) REFERENCES addresses(id)
);
