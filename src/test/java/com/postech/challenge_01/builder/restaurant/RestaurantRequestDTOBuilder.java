package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;

import java.time.LocalTime;

public class RestaurantRequestDTOBuilder {
    private Long ownerId = 1L;
    private Long addressId = 1L;
    private String name = "Restaurante Teste";
    private String type = "Tipo Teste";
    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(18, 0, 0);

    public static RestaurantRequestDTOBuilder oneRestaurantRequestDTO() {
        return new RestaurantRequestDTOBuilder();
    }

    public RestaurantRequestDTOBuilder withOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RestaurantRequestDTOBuilder withAddressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    public RestaurantRequestDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantRequestDTOBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public RestaurantRequestDTOBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public RestaurantRequestDTOBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public RestaurantRequestDTO build() {
        return new RestaurantRequestDTO(
                ownerId,
                addressId,
                name,
                type,
                startTime,
                endTime
        );
    }
}
