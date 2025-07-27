package com.postech.challenge_01.domains;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class MenuItem {
    private final Long id;
    private final Long menuId;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Boolean dineInOnly;
    @ToString.Exclude
    private final byte[] platePhotoContent;
    private final String platePhotoOriginalFilename;
    private final String platePhotoMimeType;
    private final LocalDateTime lastModifiedDateTime;

    public MenuItem(
            Long id,
            Long menuId,
            @NonNull String name,
            String description,
            @NonNull BigDecimal price,
            @NonNull Boolean dineInOnly,
            byte @NonNull [] platePhotoContent,
            @NonNull String platePhotoOriginalFilename,
            @NonNull String platePhotoMimeType,
            LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dineInOnly = dineInOnly;
        this.platePhotoContent = platePhotoContent;
        this.platePhotoOriginalFilename = platePhotoOriginalFilename;
        this.platePhotoMimeType = platePhotoMimeType;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public MenuItem(
            Long menuId,
            @NonNull String name,
            String description,
            @NonNull BigDecimal price,
            @NonNull Boolean dineInOnly,
            byte @NonNull [] platePhotoContent,
            @NonNull String platePhotoOriginalFilename,
            @NonNull String platePhotoMimeType
    ) {
        this(
                null,
                menuId,
                name,
                description,
                price,
                dineInOnly,
                platePhotoContent,
                platePhotoOriginalFilename,
                platePhotoMimeType
        );
    }

    public MenuItem(
            Long id,
            Long menuId,
            @NonNull String name,
            String description,
            @NonNull BigDecimal price,
            @NonNull Boolean dineInOnly,
            byte @NonNull [] platePhotoContent,
            @NonNull String platePhotoOriginalFilename,
            @NonNull String platePhotoMimeType
    ) {
        this(
                id,
                menuId,
                name,
                description,
                price,
                dineInOnly,
                platePhotoContent,
                platePhotoOriginalFilename,
                platePhotoMimeType,
                LocalDateTime.now()
        );
    }
}
