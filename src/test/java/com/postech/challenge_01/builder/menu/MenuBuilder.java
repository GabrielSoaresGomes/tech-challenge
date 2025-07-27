package com.postech.challenge_01.builder.menu;

import com.postech.challenge_01.domains.Menu;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuBuilder {
    private Long id = 1L;
    private Long restaurantId = 2L;
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();

    public static MenuBuilder oneMenu() {
        return new MenuBuilder();
    }

    public MenuBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MenuBuilder withRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
        return this;
    }

    public MenuBuilder withLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
        return this;
    }

    public Menu build() {
        return new Menu(
                this.id,
                this.restaurantId,
                this.lastModifiedDateTime
        );
    }
}
