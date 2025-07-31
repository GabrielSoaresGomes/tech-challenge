package com.postech.challenge_01.infrastructure.entities;

import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "restaurants")
public class RestaurantEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    UserEntity owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    AddressEntity address;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private RestaurantGenreEnum type;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public static RestaurantEntity of(final Restaurant restaurant) {
        UserEntity user = new UserEntity();
        user.setId(restaurant.getOwnerId());

        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(restaurant.getId());
        entity.setOwner(user);
        entity.setName(restaurant.getName());
        entity.setType(restaurant.getType());
        entity.setStartTime(restaurant.getStartTime());
        entity.setEndTime(restaurant.getEndTime());
        entity.setLastModifiedDateTime(restaurant.getLastModifiedDateTime());

        if (restaurant.getAddress() != null) {
            entity.setAddress(AddressEntity.of(restaurant.getAddress()));
        }

        return entity;
    }

    public Restaurant toRestaurant() {
        return new Restaurant(
                this.getId(),
                this.getOwner().getId(),
                this.getName(),
                this.getType(),
                this.getStartTime(),
                this.getEndTime(),
                this.getLastModifiedDateTime(),
                this.getAddress().toAddress()
        );
    }
}
