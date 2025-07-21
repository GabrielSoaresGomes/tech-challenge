CREATE TABLE restaurants (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    type         VARCHAR(255) NOT NULL,
    startTime    TIME WITHOUT TIME ZONE NOT NULL,
    endTime      TIME WITHOUT TIME ZONE NOT NULL,
    lastModifiedDateTime TIMESTAMP NOT NULL,
    ownerId      BIGINT NOT NULL,
    addressId    BIGINT NOT NULL,

    CONSTRAINT restaurants_owner_FK FOREIGN KEY (ownerId) REFERENCES users (id),
    CONSTRAINT restaurants_address_FK FOREIGN KEY (addressId) REFERENCES addresses (id)
);
