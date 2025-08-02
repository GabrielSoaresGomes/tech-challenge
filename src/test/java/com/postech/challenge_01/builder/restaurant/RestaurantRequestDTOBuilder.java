package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.builder.address.AddressRequestDTOBuilder;
import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;

import java.time.LocalTime;

public class RestaurantRequestDTOBuilder {
    private Long ownerId = 1L;
    private String name = "Restaurante Teste";
    private RestaurantGenreEnum type = RestaurantGenreEnum.BRAZILIAN;
    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(18, 0, 0);
    // TODO - Trocar para o Builder de Address quando tiver
    private AddressRequestDTO addressRequestDTO = AddressRequestDTOBuilder.oneAddressRequestDTO().build();

    public static RestaurantRequestDTOBuilder oneRestaurantRequestDTO() {
        return new RestaurantRequestDTOBuilder();
    }

    public RestaurantRequestDTOBuilder withOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RestaurantRequestDTOBuilder withAddressRequestDTO(AddressRequestDTO addressRequestDTO) {
        this.addressRequestDTO = addressRequestDTO;
        return this;
    }

    public RestaurantRequestDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantRequestDTOBuilder withType(RestaurantGenreEnum type) {
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
                name,
                type,
                startTime,
                endTime,
                addressRequestDTO
        );
    }
}
