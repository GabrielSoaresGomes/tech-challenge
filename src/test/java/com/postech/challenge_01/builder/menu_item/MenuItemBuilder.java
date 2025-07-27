package com.postech.challenge_01.builder.menu_item;

import com.postech.challenge_01.domains.MenuItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuItemBuilder {
    private Long id = 1L;
    private Long menuId = 2L;
    private String name = "Nome do item";
    private String description = "Descrição do item";
    private BigDecimal price = BigDecimal.valueOf(39.9);
    private Boolean dineInOnly = false;
    private byte[] platePhotoContent = new byte[] {0, 1, 0, 1, 0, 1};
    private String platePhotoOriginalFilename = "filename.png";
    private String platePhotoMimeType = "image/png";
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();

    public static MenuItemBuilder oneMenuItem() {
        return new MenuItemBuilder();
    }

    public MenuItemBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MenuItemBuilder withMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public MenuItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MenuItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MenuItemBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public MenuItemBuilder withDineInOnly(Boolean dineInOnly) {
        this.dineInOnly = dineInOnly;
        return this;
    }

    public MenuItemBuilder withPlatePhotoContent(byte[] platePhotoContent) {
        this.platePhotoContent = platePhotoContent;
        return this;
    }

    public MenuItemBuilder withPlatePhotoOriginalFilename(String platePhotoOriginalFilename) {
        this.platePhotoOriginalFilename = platePhotoOriginalFilename;
        return this;
    }

    public MenuItemBuilder withPlatePhotoMimeType(String platePhotoMimeType) {
        this.platePhotoMimeType = platePhotoMimeType;
        return this;
    }

    public MenuItemBuilder withLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
        return this;
    }

    public MenuItem build() {
        return new MenuItem(
                id,
                menuId,
                name,
                description,
                price,
                dineInOnly,
                platePhotoContent,
                platePhotoOriginalFilename,
                platePhotoMimeType,
                lastModifiedDateTime
        );
    }
}
