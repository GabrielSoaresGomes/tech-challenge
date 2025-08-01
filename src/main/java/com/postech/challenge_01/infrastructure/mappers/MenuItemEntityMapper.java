package com.postech.challenge_01.infrastructure.mappers;

import com.postech.challenge_01.dtos.transfer.menu_item.MenuItemDTO;
import com.postech.challenge_01.dtos.transfer.menu_item.NewMenuItemDTO;
import com.postech.challenge_01.infrastructure.entities.MenuEntity;
import com.postech.challenge_01.infrastructure.entities.MenuItemEntity;

import java.time.LocalDateTime;

public class MenuItemEntityMapper {
    public static MenuItemEntity toMenuItemEntity(NewMenuItemDTO dto) {
        var menu = new MenuEntity();
        menu.setId(dto.menuId());

        return new MenuItemEntity(
                null,
                menu,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.dineInOnly(),
                dto.platePhotoContent(),
                dto.platePhotoOriginalFilename(),
                dto.platePhotoMimeType(),
                LocalDateTime.now()
        );
    }

    public static MenuItemEntity toMenuItemEntity(MenuItemDTO dto) {
        var menu = new MenuEntity();
        menu.setId(dto.menuId());

        return new MenuItemEntity(
                dto.id(),
                menu,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.dineInOnly(),
                dto.platePhotoContent(),
                dto.platePhotoOriginalFilename(),
                dto.platePhotoMimeType(),
                dto.lastModifiedDate()
        );
    }

    public static MenuItemDTO toMenuItemDTO(MenuItemEntity entity) {
        return new MenuItemDTO(
                entity.getId(),
                entity.getMenu().getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDineInOnly(),
                entity.getPlatePhotoContent(),
                entity.getPlatePhotoOriginalFilename(),
                entity.getPlatePhotoMimeType(),
                entity.getLastModifiedDateTime()
        );
    }
}
