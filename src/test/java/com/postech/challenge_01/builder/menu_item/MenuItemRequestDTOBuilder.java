package com.postech.challenge_01.builder.menu_item;

import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@SuppressWarnings({"unused"})
public class MenuItemRequestDTOBuilder {
    private Long menuId = 1L;
    private String name = "Nome do item";
    private String description = "Descrição do item";
    private BigDecimal price = BigDecimal.valueOf(39.9);
    private Boolean dineInOnly = true;
    private MultipartFile platePhoto = new MockMultipartFile(
            "filename.png",
            "filename.png",
            "image/png",
            new byte[]{}
    );

    public static MenuItemRequestDTOBuilder builder() {
        return new MenuItemRequestDTOBuilder();
    }

    public MenuItemRequestDTOBuilder withMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public MenuItemRequestDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MenuItemRequestDTOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MenuItemRequestDTOBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public MenuItemRequestDTOBuilder withDineInOnly(Boolean dineInOnly) {
        this.dineInOnly = dineInOnly;
        return this;
    }

    public MenuItemRequestDTOBuilder withPlatePhoto(MultipartFile platePhoto) {
        this.platePhoto = platePhoto;
        return this;
    }

    public MenuItemRequestDTO build() {
        return new MenuItemRequestDTO(
                menuId,
                name,
                description,
                price,
                dineInOnly,
                platePhoto
        );
    }
}
