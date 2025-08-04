package com.postech.challenge_01.dtos.transfer.restaurant;

import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;

import java.time.LocalTime;

public record NewRestaurantDTO (
    Long ownerId,
    String name,
    RestaurantGenreEnum type,
    LocalTime startTime,
    LocalTime endTime,
    Long addressId
) {}
