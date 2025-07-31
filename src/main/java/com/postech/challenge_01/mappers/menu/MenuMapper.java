package com.postech.challenge_01.mappers.menu;

import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuMapper {
    public static Menu menuRequestDTOToMenu(MenuRequestDTO dto) {
        return new Menu(dto.restaurantId());
    }

    public static MenuResponseDTO menuToMenuResponseDTO(Menu menu) {
        return new MenuResponseDTO(menu.getId(), menu.getRestaurantId());
    }

    public static List<MenuResponseDTO> menusToMenuResponseDTOList(List<Menu> menus) {
        if (Objects.isNull(menus)) return null;

        return menus.stream()
                .map(MenuMapper::menuToMenuResponseDTO)
                .toList();
    }
}
