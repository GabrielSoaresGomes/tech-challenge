CREATE TABLE users_types (
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    type user_type_enum NOT NULL,
    last_modified_date_time TIMESTAMP NOT NULL
);
