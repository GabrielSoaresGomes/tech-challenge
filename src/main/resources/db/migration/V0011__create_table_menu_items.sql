CREATE TABLE menu_items (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  menu_id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NULL,
  price DECIMAL(15, 2) NOT NULL,
  dine_in_only BOOLEAN NOT NULL,
  plate_photo_content BYTEA NOT NULL,
  plate_photo_original_filename VARCHAR(255) NOT NULL,
  plate_photo_mime_type VARCHAR(255) NOT NULL,
  last_modified_date_time TIMESTAMP NOT NULL,
  CONSTRAINT menu_item_menu_fk FOREIGN KEY (menu_id) REFERENCES menus (id)
);
