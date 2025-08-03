package com.postech.challenge_01.builder.menu;

import com.postech.challenge_01.dtos.transfer.menu.MenuDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuDTOBuilder {
    private Long id = 1L;
    private Long restaurantId = 2L;
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();

    public static MenuDTOBuilder oneMenuDTO() {
        return new MenuDTOBuilder();
    }

    public MenuDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MenuDTOBuilder withRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public MenuDTOBuilder withLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
        return this;
    }

    public MenuDTO build() {
        return new MenuDTO(
                this.id,
                this.restaurantId,
                this.lastModifiedDateTime
        );
    }
}
