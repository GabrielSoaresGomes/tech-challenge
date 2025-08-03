package com.postech.challenge_01.builder.menu_item;

import com.postech.challenge_01.dtos.transfer.menu_item.NewMenuItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewMenuItemDTOBuilder {
    private Long menuId = 2L;
    private String name = "Nome do item";
    private String description = "Descrição do item";
    private BigDecimal price = BigDecimal.valueOf(39.9);
    private Boolean dineInOnly = false;
    private byte[] platePhotoContent = new byte[] {0, 1, 0, 1, 0, 1};
    private String platePhotoOriginalFilename = "filename.png";
    private String platePhotoMimeType = "image/png";

    public static NewMenuItemDTOBuilder oneMenuItem() {
        return new NewMenuItemDTOBuilder();
    }

    public NewMenuItemDTOBuilder withMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public NewMenuItemDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public NewMenuItemDTOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public NewMenuItemDTOBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public NewMenuItemDTOBuilder withDineInOnly(Boolean dineInOnly) {
        this.dineInOnly = dineInOnly;
        return this;
    }

    public NewMenuItemDTOBuilder withPlatePhotoContent(byte[] platePhotoContent) {
        this.platePhotoContent = platePhotoContent;
        return this;
    }

    public NewMenuItemDTOBuilder withPlatePhotoOriginalFilename(String platePhotoOriginalFilename) {
        this.platePhotoOriginalFilename = platePhotoOriginalFilename;
        return this;
    }

    public NewMenuItemDTOBuilder withPlatePhotoMimeType(String platePhotoMimeType) {
        this.platePhotoMimeType = platePhotoMimeType;
        return this;
    }

    public NewMenuItemDTO build() {
        return new NewMenuItemDTO(
                menuId,
                name,
                description,
                price,
                dineInOnly,
                platePhotoContent,
                platePhotoOriginalFilename,
                platePhotoMimeType
        );
    }
}
