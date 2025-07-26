package com.postech.challenge_01.entities.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.entities.AddressEntity;
import com.postech.challenge_01.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
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
    @JoinColumn(name = "ownerid", referencedColumnName = "id")
    UserEntity owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "addressid", referencedColumnName = "id")
    AddressEntity address;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, name = "starttime")
    private LocalTime startTime;

    @Column(nullable = false, name = "endtime")
    private LocalTime endTime;

    @Column(nullable = false, name = "lastmodifieddatetime")
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
