package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemEntity {
    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private Boolean dineInOnly;
    private byte[] platePhoto;
    private LocalDateTime lastModifiedDateTime;

    public static MenuItemEntity of(MenuItem menuItem) {
        return new MenuItemEntity(
                menuItem.getId(),
                menuItem.getMenuId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getDineInOnly(),
                menuItem.getPlatePhoto(),
                menuItem.getLastModifiedDateTime()
        );
    }

    public MenuItem toMenuItem() {
        return new MenuItem(
                this.id,
                this.menuId,
                this.name,
                this.description,
                this.dineInOnly,
                this.platePhoto,
                this.lastModifiedDateTime
        );
    }
}
