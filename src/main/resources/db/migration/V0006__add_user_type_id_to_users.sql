ALTER TABLE users
    ADD COLUMN user_type_id BIGINT NOT NULL,
    ADD CONSTRAINT fk_users_user_type
        FOREIGN KEY (user_type_id) REFERENCES users_types(id);
