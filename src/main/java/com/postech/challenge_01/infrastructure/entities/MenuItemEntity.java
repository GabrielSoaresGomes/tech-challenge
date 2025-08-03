package com.postech.challenge_01.infrastructure.entities;

import com.postech.challenge_01.domain.MenuItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_items")
@Entity
public class MenuItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "menu_items_menu_fk"))
    private MenuEntity menu;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "dine_in_only", nullable = false)
    private Boolean dineInOnly;

    @ToString.Exclude
    @Column(name = "plate_photo_content", nullable = false)
    private byte[] platePhotoContent;

    @Column(name = "plate_photo_original_filename", nullable = false)
    private String platePhotoOriginalFilename;

    @Column(name = "plate_photo_mime_type", nullable = false)
    private String platePhotoMimeType;

    @Column(name = "last_modified_date_time", nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public static MenuItemEntity of(MenuItem menuItem) {
        var menu = Optional.ofNullable(menuItem.getMenuId())
                .map(
                        menuId -> MenuEntity.builder()
                                .id(menuId)
                                .build()
                )
                .orElse(null);

        return new MenuItemEntity(
                menuItem.getId(),
                menu,
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getDineInOnly(),
                menuItem.getPlatePhotoContent(),
                menuItem.getPlatePhotoOriginalFilename(),
                menuItem.getPlatePhotoMimeType(),
                menuItem.getLastModifiedDateTime()
        );
    }

    public MenuItemEntity update(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.price = menuItem.getPrice();
        this.dineInOnly = menuItem.getDineInOnly();
        this.platePhotoContent = menuItem.getPlatePhotoContent();
        this.platePhotoOriginalFilename = menuItem.getPlatePhotoOriginalFilename();
        this.platePhotoMimeType = menuItem.getPlatePhotoMimeType();

        return this;
    }

    public MenuItem toMenuItem() {
        var menuId = Optional.ofNullable(this.menu)
                .map(MenuEntity::getId)
                .orElse(null);

        return new MenuItem(
                this.id,
                menuId,
                this.name,
                this.description,
                this.price,
                this.dineInOnly,
                this.platePhotoContent,
                this.platePhotoOriginalFilename,
                this.platePhotoMimeType,
                this.lastModifiedDateTime
        );
    }
}
