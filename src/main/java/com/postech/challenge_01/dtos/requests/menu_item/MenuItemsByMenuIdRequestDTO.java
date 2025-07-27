package com.postech.challenge_01.dtos.requests.menu_item;

import com.fasterxml.jackson.annotation.JsonAlias;

public record MenuItemsByMenuIdRequestDTO(
        @JsonAlias("id") Long menuId,
        int page,
        int size
) {
}
