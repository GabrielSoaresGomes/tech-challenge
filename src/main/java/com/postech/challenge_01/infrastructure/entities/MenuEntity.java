package com.postech.challenge_01.infrastructure.entities;

import com.postech.challenge_01.domain.Menu;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "menu_restaurant_fk"), nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "last_modified_date_time", nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = CascadeType.REMOVE)
    private List<MenuItemEntity> menuItems = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public static MenuEntity of(final Menu menu) {
        var restaurant = Optional.ofNullable(menu.getRestaurantId())
                .map(
                        restaurantId -> RestaurantEntity.builder()
                                .id(restaurantId)
                                .build()
                )
                .orElse(null);

        return new MenuEntity(
                menu.getId(),
                restaurant,
                menu.getLastModifiedDateTime(),
                List.of()
        );
    }

    public Menu toMenu() {
        return new Menu(
                this.id,
                this.restaurant.getId(),
                this.lastModifiedDateTime
        );
    }
}
