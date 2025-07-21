package com.postech.challenge_01.mappers.meu_item;

import com.postech.challenge_01.domains.menu_item.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuItemMapper {
    public static MenuItem menuItemRequestDTOToMenuItem(MenuItemRequestDTO dto) {
        return MenuItemMapper.menuItemRequestDTOToMenuItem(null, dto);
    }

    public static MenuItem menuItemRequestDTOToMenuItem(Long id, MenuItemRequestDTO dto) {
        return new MenuItem(
                id,
                dto.menuId(),
                dto.name(),
                dto.description(),
                dto.dineInOnly(),
                dto.platePhoto()
        );
    }

    public static MenuItemResponseDTO menuItemToMenuItemResponseDTO(MenuItem menuItem) {
        return new MenuItemResponseDTO(
                menuItem.getId(),
                menuItem.getMenuId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getDineInOnly(),
                menuItem.getPlatePhoto()
        );
    }

    public static List<MenuItemResponseDTO> menuItemsToMenuItemResponseDTOList(List<MenuItem> menuItems) {
        if (Objects.isNull(menuItems)) return null;

        return menuItems.stream()
                .map(MenuItemMapper::menuItemToMenuItemResponseDTO)
                .toList();
    }
}
