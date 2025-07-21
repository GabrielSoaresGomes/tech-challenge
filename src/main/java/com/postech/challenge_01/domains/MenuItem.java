package com.postech.challenge_01.domains;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class MenuItem {
    private final Long id;
    private final Long menuId;
    private final String name;
    private final String description;
    private final Boolean dineInOnly;
    private final byte[] platePhoto;
    private final LocalDateTime lastModifiedDateTime;

    public MenuItem(
            Long id,
            @NonNull Long menuId,
            @NonNull String name,
            String description,
            @NonNull Boolean dineInOnly,
            byte @NonNull [] platePhoto,
            LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.dineInOnly = dineInOnly;
        this.platePhoto = platePhoto;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public MenuItem(
            @NonNull Long menuId,
            @NonNull String name,
            String description,
            @NonNull Boolean dineInOnly,
            byte @NonNull [] platePhoto
    ) {
        this(null, menuId, name, description, dineInOnly, platePhoto);
    }

    public MenuItem(
            Long id,
            @NonNull Long menuId,
            @NonNull String name,
            String description,
            @NonNull Boolean dineInOnly,
            byte @NonNull [] platePhoto
    ) {
        this(id, menuId, name, description, dineInOnly, platePhoto, LocalDateTime.now());
    }
}
