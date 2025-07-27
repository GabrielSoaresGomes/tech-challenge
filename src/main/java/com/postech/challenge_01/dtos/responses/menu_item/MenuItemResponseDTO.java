package com.postech.challenge_01.dtos.responses.menu_item;

import java.math.BigDecimal;

public record MenuItemResponseDTO(
        Long id,
        Long menuId,
        String name,
        String description,
        BigDecimal price,
        Boolean dineInOnly
) {
}
