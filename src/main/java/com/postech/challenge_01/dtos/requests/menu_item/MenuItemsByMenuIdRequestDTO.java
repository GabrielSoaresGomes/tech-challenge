package com.postech.challenge_01.dtos.requests.menu_item;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.data.domain.Pageable;

public record MenuItemsByMenuIdRequestDTO(
        @JsonAlias("id") Long menuId,
        Pageable pageable
) {
}
