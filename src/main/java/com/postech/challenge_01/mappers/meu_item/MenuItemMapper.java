package com.postech.challenge_01.mappers.meu_item;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuItemMapper {
    public static MenuItem menuItemRequestDTOToMenuItem(MenuItemRequestDTO dto) throws IOException {
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

    public static MenuItem menuItemUpdateRequestDTOToMenuItem(MenuItemUpdateRequestDTO dto) throws IOException {
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

    public static MenuItemResponseDTO menuItemToMenuItemResponseDTO(MenuItem menuItem) {
        return new MenuItemResponseDTO(
                menuItem.getId(),
                menuItem.getMenuId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getDineInOnly()
        );
    }

    public static List<MenuItemResponseDTO> menuItemsToMenuItemResponseDTOList(List<MenuItem> menuItems) {
        if (Objects.isNull(menuItems)) return null;

        return menuItems.stream()
                .map(MenuItemMapper::menuItemToMenuItemResponseDTO)
                .toList();
    }
}
