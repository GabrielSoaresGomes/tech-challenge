package com.postech.challenge_01.dtos.transfer.menu_item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MenuItemDTO(
        Long id,
        Long menuId,
        String name,
        String description,
        BigDecimal price,
        Boolean dineInOnly,
        byte[] platePhotoContent,
        String platePhotoOriginalFilename,
        String platePhotoMimeType,
        LocalDateTime lastModifiedDate
) {
}
