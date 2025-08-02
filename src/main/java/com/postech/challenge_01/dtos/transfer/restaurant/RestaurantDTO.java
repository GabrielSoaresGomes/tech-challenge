package com.postech.challenge_01.dtos.transfer.restaurant;

import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record RestaurantDTO (
        Long id,
        Long ownerId,
        String name,
        RestaurantGenreEnum type,
        LocalTime startTime,
        LocalTime endTime,
        Long addressId,
        LocalDateTime lastModifiedDateTime
) {}
