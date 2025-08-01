package com.postech.challenge_01.interface_adapter.presenters;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;

import java.util.List;
import java.util.Objects;

public class MenuItemPresenter {
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
                .map(MenuItemPresenter::menuItemToMenuItemResponseDTO)
                .toList();
    }
}
