package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.Restaurant;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RestaurantEntity {
    private Long id;
    private Long ownerId;
    private Long addressId;
    private String name;
    private String type;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime lastModifiedDateTime;

    public static RestaurantEntity of(final Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getId(),
                restaurant.getOwnerId(),
                restaurant.getAddressId(),
                restaurant.getName(),
                restaurant.getType(),
                restaurant.getStartTime(),
                restaurant.getEndTime(),
                restaurant.getLastModifiedDateTime()
        );
    }

    public Restaurant toRestaurant() {
        return new Restaurant(
                this.getId(),
                this.getOwnerId(),
                this.getAddressId(),
                this.getName(),
                this.getType(),
                this.getStartTime(),
                this.getEndTime(),
                this.getLastModifiedDateTime()
        );
    }
}
