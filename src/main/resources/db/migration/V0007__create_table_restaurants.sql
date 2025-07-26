CREATE TABLE restaurants (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    type          VARCHAR(255) NOT NULL,
    start_time    TIME WITHOUT TIME ZONE NOT NULL,
    end_time      TIME WITHOUT TIME ZONE NOT NULL,
    last_modified_date_time TIMESTAMP NOT NULL,
    owner_id      BIGINT NOT NULL,
    address_id    BIGINT NOT NULL,

    CONSTRAINT restaurants_owner_FK FOREIGN KEY (owner_id) REFERENCES users (id),
    CONSTRAINT restaurants_address_FK FOREIGN KEY (address_id) REFERENCES addresses (id)
);
