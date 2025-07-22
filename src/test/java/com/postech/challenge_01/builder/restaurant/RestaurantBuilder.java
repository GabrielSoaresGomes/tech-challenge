package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.domains.Restaurant;

import java.time.LocalTime;

public class RestaurantBuilder {
    private Long id;
    private Long ownerId = 1L;
    private Long addressId = 1L;
    private String name = "Restaurante Teste";
    private String type = "Tipo Teste";
    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(18, 0, 0);

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

    public RestaurantBuilder withAddressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    public RestaurantBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantBuilder withType(String type) {
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

    public Restaurant build() {
        return new Restaurant(
                id,
                ownerId,
                addressId,
                name,
                type,
                startTime,
                endTime
        );
    }

}
