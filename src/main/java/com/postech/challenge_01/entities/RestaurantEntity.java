package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
        AddressEntity address = new AddressEntity();
        address.setId(restaurant.getAddressId());

        UserEntity user = new UserEntity();
        user.setId(restaurant.getOwnerId());

        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(restaurant.getId());
        entity.setOwner(user);
        entity.setAddress(address);
        entity.setName(restaurant.getName());
        entity.setType(restaurant.getType());
        entity.setStartTime(restaurant.getStartTime());
        entity.setEndTime(restaurant.getEndTime());
        entity.setLastModifiedDateTime(restaurant.getLastModifiedDateTime());

        return entity;
    }

    public Restaurant toRestaurant() {
        return new Restaurant(
                this.getId(),
                this.getOwner().getId(),
                this.getAddress().getId(),
                this.getName(),
                this.getType(),
                this.getStartTime(),
                this.getEndTime(),
                this.getLastModifiedDateTime()
        );
    }
}
