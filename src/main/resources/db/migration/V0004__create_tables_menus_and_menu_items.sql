CREATE TABLE menus (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  restaurantId BIGINT NOT NULL,
  lastModifiedDateTime TIMESTAMP NOT NULL,
  CONSTRAINT menu_restaurant_fk FOREIGN KEY (restaurantId) REFERENCES restaurants (id)
);

CREATE TABLE menu_items (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NULL,
  price DECIMAL(15, 2) NOT NULL,
  dineInOnly BOOLEAN NOT NULL,
  platePhoto BYTEA NULL,
  menuId BIGINT NOT NULL,
  lastModifiedDateTime TIMESTAMP NOT NULL,
  CONSTRAINT menu_item_menu_fk FOREIGN KEY (menuId) REFERENCES menus (id)
);
