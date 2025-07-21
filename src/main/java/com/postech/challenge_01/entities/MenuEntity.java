package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.Menu;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MenuEntity {
    private Long id;
    private Long restaurantId;
    private LocalDateTime lastModifiedDateTime;

    public static MenuEntity of(final Menu menu) {
        return new MenuEntity(
                menu.getId(),
                menu.getRestaurantId(),
                menu.getLastModifiedDateTime()
        );
    }

    public Menu toMenu() {
        return new Menu(
                this.id,
                this.restaurantId,
                this.lastModifiedDateTime
        );
    }
}
