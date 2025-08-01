package com.postech.challenge_01.mappers.meu_item;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.dtos.transfer.menu_item.MenuItemDTO;
import com.postech.challenge_01.dtos.transfer.menu_item.NewMenuItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuItemMapper {
    public static MenuItem toMenuItem(MenuItemRequestDTO dto) throws IOException {
        var platePhotoOriginalFilename = Optional.of(dto.platePhoto())
                .map(MultipartFile::getOriginalFilename)
                .orElseThrow(NullPointerException::new);
        var platePhotoMimeType = Optional.of(dto.platePhoto())
                .map(MultipartFile::getContentType)
                .orElseThrow(NullPointerException::new);

        return new MenuItem(
                dto.menuId(),
                dto.name(),
                dto.description(),
                dto.price(),
                dto.dineInOnly(),
                dto.platePhoto().getBytes(),
                platePhotoOriginalFilename,
                platePhotoMimeType
        );
    }

    public static MenuItem toMenuItem(MenuItemUpdateRequestDTO dto) throws IOException {
        var platePhotoOriginalFilename = Optional.of(dto.platePhoto())
                .map(MultipartFile::getOriginalFilename)
                .orElseThrow(NullPointerException::new);
        var platePhotoMimeType = Optional.of(dto.platePhoto())
                .map(MultipartFile::getContentType)
                .orElseThrow(NullPointerException::new);

        return new MenuItem(
                dto.id(),
                null,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.dineInOnly(),
                dto.platePhoto().getBytes(),
                platePhotoOriginalFilename,
                platePhotoMimeType
        );
    }

    public static MenuItem toMenuItem(MenuItemDTO dto) {
        return new MenuItem(
                dto.id(),
                dto.menuId(),
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

    public static NewMenuItemDTO toNewMenuItemDTO(MenuItem menuItem) {
        return new NewMenuItemDTO(
                menuItem.getMenuId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getDineInOnly(),
                menuItem.getPlatePhotoContent(),
                menuItem.getPlatePhotoOriginalFilename(),
                menuItem.getPlatePhotoMimeType()
        );
    }

    public static MenuItemDTO toMenuItemDTO(MenuItem menuItem, Long menuId, Long id) {
        return new MenuItemDTO(
                id,
                menuId,
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getDineInOnly(),
                menuItem.getPlatePhotoContent(),
                menuItem.getPlatePhotoOriginalFilename(),
                menuItem.getPlatePhotoMimeType(),
                LocalDateTime.now()
        );
    }
}
