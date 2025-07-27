package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.Menu;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
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

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public static MenuEntity of(final Menu menu) {
        var restaurant = new RestaurantEntity();
        restaurant.setId(menu.getRestaurantId());

        return new MenuEntity(
                menu.getId(),
                restaurant,
                menu.getLastModifiedDateTime()
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
