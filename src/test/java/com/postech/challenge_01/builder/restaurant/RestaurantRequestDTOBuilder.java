package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RestaurantRequestDTOBuilder {
    private Long ownerId = 1L;
    private String name = "Restaurante Teste";
    private String type = "Tipo Teste";
    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(18, 0, 0);
    // TODO - Trocar para o Builder de Address quando tiver
    private Address address = new Address(null, "Rua Teste", "Número teste",
            "Complemento teste", "Bairro Teste", "Cidade Teste", "Estado Teste",
            "País Teste", "CEP Teste",
            LocalDateTime.of(2025, 7, 24, 23, 50, 0, 0)
    );

    public static RestaurantRequestDTOBuilder oneRestaurantRequestDTO() {
        return new RestaurantRequestDTOBuilder();
    }

    public RestaurantRequestDTOBuilder withOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RestaurantRequestDTOBuilder withAddress(Address address) {
        this.address = address;
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
                name,
                type,
                startTime,
                endTime,
                address
        );
    }
}
