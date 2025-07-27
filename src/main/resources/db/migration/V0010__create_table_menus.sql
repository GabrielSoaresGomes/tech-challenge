CREATE TABLE menus (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  restaurant_id BIGINT NOT NULL,
  last_modified_date_time TIMESTAMP NOT NULL,
  CONSTRAINT menu_restaurant_fk FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
);
