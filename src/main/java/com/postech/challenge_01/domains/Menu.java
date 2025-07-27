package com.postech.challenge_01.domains;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class Menu {
    private final Long id;
    private final Long restaurantId;
    private final LocalDateTime lastModifiedDateTime;

    public Menu(
            Long id,
            @NonNull Long restaurantId,
            LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Menu(@NonNull Long restaurantId) {
        this(null, restaurantId, LocalDateTime.now());
    }
}
