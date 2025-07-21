ALTER TABLE users
    ADD COLUMN userTypeId BIGINT NOT NULL,
    ADD CONSTRAINT fk_users_user_type
        FOREIGN KEY (userTypeId) REFERENCES user_type(id);
