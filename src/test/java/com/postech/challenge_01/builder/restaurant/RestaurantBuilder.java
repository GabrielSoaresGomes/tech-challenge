package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.domains.enums.RestaurantGenreEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RestaurantBuilder {
    private Long id;
    private Long ownerId = 1L;
    private String name = "Restaurante Teste";
    private RestaurantGenreEnum type = RestaurantGenreEnum.BRAZILIAN;
    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(18, 0, 0);
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();
    // TODO - Trocar para o Builder de Address quando tiver
    private Address address = new Address(1L, "Rua Teste", "Número teste",
            "Complemento teste", "Bairro Teste", "Cidade Teste", "Estado Teste",
            "País Teste", "CEP Teste",
            LocalDateTime.of(2025, 7, 24, 23, 50, 0, 0)
    );

    public static RestaurantBuilder oneRestaurant() {
        return new RestaurantBuilder();
    }

    public RestaurantBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RestaurantBuilder withOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RestaurantBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public RestaurantBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantBuilder withType(RestaurantGenreEnum type) {
        this.type = type;
        return this;
    }

    public RestaurantBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public RestaurantBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public RestaurantBuilder withLastModifiedDateTime(LocalDateTime dateTime) {
        this.lastModifiedDateTime = dateTime;
        return this;
    }

    public Restaurant build() {
        return new Restaurant(
                id,
                ownerId,
                name,
                type,
                startTime,
                endTime,
                lastModifiedDateTime,
                address
        );
    }

}
