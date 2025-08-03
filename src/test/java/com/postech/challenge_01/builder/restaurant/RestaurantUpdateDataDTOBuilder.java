package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateDataDTO;

import java.time.LocalTime;

public class RestaurantUpdateDataDTOBuilder {
    private Long ownerId = 1L;
    private String name = "Restaurante Atualizado";
    private RestaurantGenreEnum type = RestaurantGenreEnum.BRAZILIAN;
    private LocalTime startTime = LocalTime.of(10, 0);
    private LocalTime endTime = LocalTime.of(22, 0);

    public static RestaurantUpdateDataDTOBuilder oneRestaurantUpdateDataDTO() {
        return new RestaurantUpdateDataDTOBuilder();
    }

    public RestaurantUpdateDataDTOBuilder withOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RestaurantUpdateDataDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantUpdateDataDTOBuilder withType(RestaurantGenreEnum type) {
        this.type = type;
        return this;
    }

    public RestaurantUpdateDataDTOBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public RestaurantUpdateDataDTOBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public RestaurantUpdateDataDTO build() {
        return new RestaurantUpdateDataDTO(
                ownerId,
                name,
                type,
                startTime,
                endTime
        );
    }
}

