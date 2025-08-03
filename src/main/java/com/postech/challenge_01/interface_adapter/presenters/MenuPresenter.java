package com.postech.challenge_01.interface_adapter.presenters;

import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;

import java.util.List;
import java.util.Objects;

public class MenuPresenter {
    public static MenuResponseDTO menuToMenuResponseDTO(Menu menu) {
        return new MenuResponseDTO(menu.getId(), menu.getRestaurantId());
    }

    public static List<MenuResponseDTO> menusToMenuResponseDTOList(List<Menu> menus) {
        if (Objects.isNull(menus)) return null;

        return menus.stream()
                .map(MenuPresenter::menuToMenuResponseDTO)
                .toList();
    }
}
