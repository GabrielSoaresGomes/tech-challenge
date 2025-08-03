package com.postech.challenge_01.dtos.responses;

import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;

import java.time.LocalTime;

public record RestaurantResponseDTO(
        Long id,
        Long ownerId,
        String name,
        RestaurantGenreEnum type,
        LocalTime startTime,
        LocalTime endTime,
        Long addressId
) {
}
