package com.postech.challenge_01.dtos.responses.menu_item;

public record MenuItemResponseDTO(
        Long id,
        Long menuId,
        String name,
        String description,
        Boolean dineInOnly,
        byte[] platePhoto
) {
}
