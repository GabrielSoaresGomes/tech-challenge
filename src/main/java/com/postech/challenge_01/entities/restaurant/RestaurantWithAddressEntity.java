package com.postech.challenge_01.entities.restaurant;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.domains.Restaurant;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class RestaurantWithAddressEntity {
    private Long id;
    private Long ownerId;
    private String name;
    private String type;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime lastModifiedDateTime;
    private Address address;

    public Restaurant toRestaurant() {
        return new Restaurant(
                this.getId(),
                this.getOwnerId(),
                this.getName(),
                this.getType(),
                this.getStartTime(),
                this.getEndTime(),
                this.getLastModifiedDateTime()
        );
    }
}
