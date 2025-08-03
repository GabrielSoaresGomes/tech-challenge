package com.postech.challenge_01.dtos.transfer.menu_item;

import java.math.BigDecimal;

public record NewMenuItemDTO(
        Long menuId,
        String name,
        String description,
        BigDecimal price,
        Boolean dineInOnly,
        byte[] platePhotoContent,
        String platePhotoOriginalFilename,
        String platePhotoMimeType
) {
}
