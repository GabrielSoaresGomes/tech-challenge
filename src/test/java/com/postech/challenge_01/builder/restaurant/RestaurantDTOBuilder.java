package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.dtos.transfer.restaurant.RestaurantDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RestaurantDTOBuilder {
    private Long id = 1L;
    private Long ownerId = 1L;
    private String name = "Restaurante Teste";
    private RestaurantGenreEnum type = RestaurantGenreEnum.BRAZILIAN;
    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(18, 0, 0);
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();
    // TODO - Trocar para o Builder de Address quando tiver
    private Long addressId = 1L;

    public static RestaurantDTOBuilder oneRestaurantDTO() {
        return new RestaurantDTOBuilder();
    }

    public RestaurantDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RestaurantDTOBuilder withOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RestaurantDTOBuilder withAddressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    public RestaurantDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantDTOBuilder withType(RestaurantGenreEnum type) {
        this.type = type;
        return this;
    }

    public RestaurantDTOBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public RestaurantDTOBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public RestaurantDTOBuilder withLastModifiedDateTime(LocalDateTime dateTime) {
        this.lastModifiedDateTime = dateTime;
        return this;
    }

    public RestaurantDTO build() {
        return new RestaurantDTO(
                id,
                ownerId,
                name,
                type,
                startTime,
                endTime,
                addressId,
                lastModifiedDateTime
        );
    }

}
