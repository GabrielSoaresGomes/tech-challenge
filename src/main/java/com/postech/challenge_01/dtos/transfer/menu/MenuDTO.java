package com.postech.challenge_01.dtos.transfer.menu;

import java.time.LocalDateTime;

public record MenuDTO(
        Long id,
        Long restaurantId,
        LocalDateTime lastModifiedDate
) {
}
