package com.postech.challenge_01.mappers.menu;

import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.transfer.menu.MenuDTO;
import com.postech.challenge_01.dtos.transfer.menu.NewMenuDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuMapper {
    public static Menu toMenu(MenuRequestDTO dto) {
        return new Menu(dto.restaurantId());
    }

    public static Menu toMenu(MenuDTO dto) {
        return new Menu(
                dto.id(),
                dto.restaurantId(),
                dto.lastModifiedDate()
        );
    }

    public static NewMenuDTO toNewMenuDTO(Menu menu) {
        return new NewMenuDTO(
                menu.getRestaurantId()
        );
    }
}
