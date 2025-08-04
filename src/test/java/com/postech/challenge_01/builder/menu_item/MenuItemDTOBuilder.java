package com.postech.challenge_01.builder.menu_item;

import com.postech.challenge_01.dtos.transfer.menu_item.MenuItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuItemDTOBuilder {
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

    public static MenuItemDTOBuilder oneMenuItemDTO() {
        return new MenuItemDTOBuilder();
    }

    public MenuItemDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MenuItemDTOBuilder withMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public MenuItemDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MenuItemDTOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MenuItemDTOBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public MenuItemDTOBuilder withDineInOnly(Boolean dineInOnly) {
        this.dineInOnly = dineInOnly;
        return this;
    }

    public MenuItemDTOBuilder withPlatePhotoContent(byte[] platePhotoContent) {
        this.platePhotoContent = platePhotoContent;
        return this;
    }

    public MenuItemDTOBuilder withPlatePhotoOriginalFilename(String platePhotoOriginalFilename) {
        this.platePhotoOriginalFilename = platePhotoOriginalFilename;
        return this;
    }

    public MenuItemDTOBuilder withPlatePhotoMimeType(String platePhotoMimeType) {
        this.platePhotoMimeType = platePhotoMimeType;
        return this;
    }

    public MenuItemDTOBuilder withLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
        return this;
    }

    public MenuItemDTO build() {
        return new MenuItemDTO(
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
